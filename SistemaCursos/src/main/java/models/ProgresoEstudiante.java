package models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Progreso persistente de un estudiante en un curso.
 * <p>Reglas:
 * <ul>
 *   <li><b>Secuencial / Aleatorio</b>: la tarjeta cuenta
 *       como completada al responderla.</li>
 *   <li><b>Repetición</b>: solo cuenta cuando se acierta.</li>
 * </ul>
 */
@Entity @Table(name = "progresos")
public class ProgresoEstudiante {

	/* ──────────── columnas ──────────── */

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false) private Estudiante estudiante;
	@ManyToOne(optional = false) private Curso      curso;

	private int totalRespondidas;
	private int aciertos;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "progreso_flashcards",
	                 joinColumns = @JoinColumn(name = "progreso_id"))
	@Column(name = "flashcard_id")
	private Set<Long> flashcardsCompletadas = new HashSet<>();

	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaActividad;

	/* ──────────── constructores ──────────── */

	protected ProgresoEstudiante() { }                 // JPA

	public ProgresoEstudiante(Estudiante est, Curso cur) {
		this.estudiante      = est;
		this.curso           = cur;
		this.ultimaActividad = new Date();
	}

	/* ──────────── dominio ──────────── */

	/** Registra la respuesta dada a una flashcard. */
	public void registrarRespuesta(Flashcard f, boolean correcta) {
		totalRespondidas++;
		if (correcta) aciertos++;

		/* Solo marcar completada si procede según la estrategia */
		if (curso.getTipoEstrategia() != Curso.TipoEstrategia.REPETICION || correcta) {
			flashcardsCompletadas.add(f.getId());
		}
		ultimaActividad = new Date();
	}

	/** Reinicia todo el progreso del curso. */
	public void reset() {
		totalRespondidas = 0;
		aciertos         = 0;
		flashcardsCompletadas.clear();
		ultimaActividad  = new Date();
	}

	/* ──────────── métricas ──────────── */

	/**
	 * Porcentaje de progreso (0 – 1) en función de las flashcards
	 * marcadas como completadas.
	 */
	public double obtenerPorcentajeProgreso() {
		int total = curso.getFlashcards().size();
		return total == 0 ? 0.0 : flashcardsCompletadas.size() / (double) total;
	}

	/* ──────────── getters ──────────── */

	public Long       getId()               { return id; }
	public Estudiante getEstudiante()       { return estudiante; }
	public Curso      getCurso()            { return curso; }
	public int        getTotalRespondidas() { return totalRespondidas; }
	public int        getAciertos()         { return aciertos; }
	public Set<Long>  getFlashcardsCompletadas() { return flashcardsCompletadas; }

	/* ──────────── setters para tests ──────────── */

	public void setAciertos(int x)          { this.aciertos = x; }
	public void setTotalRespondidas(int x)  { this.totalRespondidas = x; }
}
