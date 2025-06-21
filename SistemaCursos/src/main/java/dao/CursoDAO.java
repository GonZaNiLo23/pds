package dao;

import models.Curso;
import models.Estudiante;

import javax.persistence.*;
import java.util.List;

/** DAO básico para la entidad {@link Curso}. */
public class CursoDAO {

	private static final EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("flashcards-jpa");

	/* ───────── guardar ───────── */

	public void guardar(Curso c) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if (c.getId() == null) em.persist(c); else em.merge(c);
		em.getTransaction().commit();
		em.close();
	}

	/* ───────── consulta única global ───────── */

	/** 
	 * CONSULTA ÚNICA: Devuelve cursos propios + cursos inscritos del usuario
	 * Optimizada con una sola query
	 */
	public List<Curso> buscarCursosDelUsuario(Estudiante estudiante) {
		EntityManager em = emf.createEntityManager();
		List<Curso> cursos = em.createQuery("""
				SELECT DISTINCT c FROM Curso c 
				LEFT JOIN Inscripcion i ON i.curso = c AND i.estudiante = :estudiante
				WHERE c.creador = :estudiante 
				   OR (i.estudiante = :estudiante AND c.creador != :estudiante)
				ORDER BY c.fechaCreacion DESC
				""", Curso.class)
				.setParameter("estudiante", estudiante)
				.getResultList();
		em.close();
		return cursos;
	}

	/** 
	 * Consulta para ventana de inscripción: cursos públicos de otros usuarios
	 * donde el estudiante NO está inscrito
	 */
	public List<Curso> buscarCursosPublicosDisponibles(Estudiante estudiante) {
		EntityManager em = emf.createEntityManager();
		List<Curso> cursos = em.createQuery("""
				SELECT c FROM Curso c 
				WHERE c.creador != :estudiante 
				  AND c.esPublico = true
				  AND c.id NOT IN (
				      SELECT i.curso.id FROM Inscripcion i 
				      WHERE i.estudiante = :estudiante
				  )
				ORDER BY c.fechaCreacion DESC
				""", Curso.class)
				.setParameter("estudiante", estudiante)
				.getResultList();
		em.close();
		return cursos;
	}

	/* ───────── consultas auxiliares (mantener para compatibilidad) ───────── */

	/** Devuelve todos los cursos ordenados por fecha de creación descendente. */
	public List<Curso> buscarTodos() {
		EntityManager em = emf.createEntityManager();
		List<Curso> l = em.createQuery("""
				SELECT c FROM Curso c
				ORDER BY c.fechaCreacion DESC
				""", Curso.class).getResultList();
		em.close();
		return l;
	}

	/* ───────── util ───────── */

	/** Llamar al cerrar la aplicación para liberar la conexión H2. */
	public static void cerrarFactory() {
		if (emf.isOpen()) emf.close();
	}
}
