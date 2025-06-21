package estrategias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Flashcard;

public class EstrategiaAleatoria implements EstrategiaAprendizaje{
	private List<Flashcard> flashcards;
	private int totalRespondidas;

	@Override
	public void inicializar(List<Flashcard> flashcards) {
		this.flashcards = new ArrayList<>(flashcards);
		Collections.shuffle(this.flashcards);
		this.totalRespondidas = 0;
	}

	@Override
	public Flashcard siguienteFlashcard() {
		if (!hayMasFlashcards()) {
			return null;
		}
		return flashcards.get(totalRespondidas);
	}

	@Override
	public void registrarRespuesta(Flashcard flashcard, boolean correcta) {
		totalRespondidas++;
	}

	@Override
	public boolean hayMasFlashcards() {
		return totalRespondidas < flashcards.size();
	}

	@Override
	public double obtenerProgreso() {
		if (flashcards == null || flashcards.isEmpty()) {
			return 0.0;
		}
		return (double) totalRespondidas / flashcards.size();
	}
}
