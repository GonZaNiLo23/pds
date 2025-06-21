package dao;

import java.util.List;

import javax.persistence.*;

import models.Curso;
import models.Estudiante;
import models.Inscripcion;

public class InscripcionDAO {
	private static final EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("flashcards-jpa");

	/* ───────── guardar ───────── */

	public void guardar(Inscripcion inscripcion) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if (inscripcion.getId() == null) {
			em.persist(inscripcion);
		} else {
			em.merge(inscripcion);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* ───────── consultar ───────── */

	public List<Inscripcion> buscarPorEstudiante(Estudiante estudiante) {
		EntityManager em = emf.createEntityManager();
		List<Inscripcion> inscripciones = em.createQuery("""
				SELECT i FROM Inscripcion i 
				WHERE i.estudiante = :estudiante
				ORDER BY i.fechaInicio DESC
				""", Inscripcion.class)
				.setParameter("estudiante", estudiante)
				.getResultList();
		em.close();
		return inscripciones;
	}

	public boolean estaInscrito(Estudiante estudiante, Curso curso) {
		EntityManager em = emf.createEntityManager();
		long count = em.createQuery("""
				SELECT COUNT(i) FROM Inscripcion i 
				WHERE i.estudiante = :estudiante AND i.curso = :curso
				""", Long.class)
				.setParameter("estudiante", estudiante)
				.setParameter("curso", curso)
				.getSingleResult();
		em.close();
		return count > 0;
	}

	public void eliminar(Estudiante estudiante, Curso curso) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("""
				DELETE FROM Inscripcion i 
				WHERE i.estudiante = :estudiante AND i.curso = :curso
				""")
		.setParameter("estudiante", estudiante)
		.setParameter("curso", curso)
		.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	/* ───────── util ───────── */

	public static void cerrarFactory() {
		if (emf.isOpen()) emf.close();
	}
}
