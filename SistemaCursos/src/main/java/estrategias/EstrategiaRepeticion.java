package estrategias;

import models.Flashcard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Estrategia de aprendizaje "Repetición de errores":
 *   – Ronda inicial secuencial.
 *   – Cada fallo se guarda.
 *   – Cuando termina la ronda inicial, se
 *     repasan únicamente los fallados hasta
 *     acertarlos todos.
 */
public class EstrategiaRepeticion implements EstrategiaAprendizaje {

	/** Cola de trabajo de la ronda actual */
	private final Deque<Flashcard> cola = new ArrayDeque<>();

	/** Lista de flashcards falladas en la ronda actual */
	private final List<Flashcard> pendientes = new ArrayList<>();

	private boolean modoRepeticion = false;

	private int totalFlashcards = 0;
	private int correctas = 0;

	/* ────────────────────── API ────────────────────── */

	@Override
	public void inicializar(List<Flashcard> lista) {
		cola.clear();
		cola.addAll(lista);           // orden secuencial
		pendientes.clear();
		modoRepeticion = false;
		totalFlashcards = lista.size();
	}

	@Override
	public boolean hayMasFlashcards() {
		return !cola.isEmpty() || !pendientes.isEmpty();
	}

	@Override
	public Flashcard siguienteFlashcard() {
		if (cola.isEmpty() && !pendientes.isEmpty()) {
			// pasar a modo repetición
			cola.addAll(pendientes);
			pendientes.clear();
			modoRepeticion = true;
		}
		return cola.pollFirst();      // puede devolver null si ya no hay nada
	}

	@Override
	public void registrarRespuesta(Flashcard f, boolean correcta) {
		if (!correcta) {
			pendientes.add(f);
			return;
		}
		correctas++;
	}

	@Override
	public double obtenerProgreso() {
		if (totalFlashcards == 0) {
			return 0.0;
		}
		return (double) correctas / totalFlashcards;
	}

	/* ───────── info para la UI ───────── */

	public boolean estaEnModoRepeticion()   { return modoRepeticion; }
	public int     getCantidadPendientes()  { return pendientes.size(); }
}
