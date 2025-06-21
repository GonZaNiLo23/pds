package models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class FlashcardTest {

	@Test
	public void crearCompletarHueco_creaFlashcardCorrectamente() {
		Flashcard f = Flashcard.crearCompletarHueco("La capital de Francia es París", "París", List.of("Londres", "Berlín"));

		assertEquals("La capital de Francia es ___", f.getPregunta());
		assertEquals("París", f.getRespuestaCorrecta());
		assertEquals(3, f.getOpciones().size());
		assertTrue(f.getOpciones().contains("Londres"));
		assertEquals(Flashcard.TipoFlashcard.COMPLETAR_HUECO, f.getTipo());
	}

	@Test
	void crearVerdaderoFalso_Verdadero() {
		Flashcard f = Flashcard.crearVerdaderoFalso("El agua hierve a 100°C", true);

		assertEquals("El agua hierve a 100°C", f.getPregunta());
		assertEquals("Verdadero", f.getRespuestaCorrecta());
		assertTrue(f.getOpciones().contains("Falso"));
		assertEquals(Flashcard.TipoFlashcard.VERDADERO_FALSO, f.getTipo());
	}

	@Test
	void crearVerdaderoFalso_Falso() {
		Flashcard f = Flashcard.crearVerdaderoFalso("El agua hierve a 20°C", false);

		assertEquals("El agua hierve a 20°C", f.getPregunta());
		assertEquals("Falso", f.getRespuestaCorrecta());
		assertTrue(f.getOpciones().contains("Verdadero"));
		assertEquals(Flashcard.TipoFlashcard.VERDADERO_FALSO, f.getTipo());
	}

	@Test
	void crearPreguntaAbierta_noTieneOpciones() {
		Flashcard f = Flashcard.crearPreguntaAbierta("¿Qué es Java?", "Un lenguaje de programación");

		assertEquals("¿Qué es Java?", f.getPregunta());
		assertEquals("Un lenguaje de programación", f.getRespuestaCorrecta());
		assertTrue(f.getOpciones().isEmpty());
		assertEquals(Flashcard.TipoFlashcard.PREGUNTA_ABIERTA, f.getTipo());
	}

	@Test
	void crearMultiopcion_creaFlashcardConOpciones() {
		Flashcard f = Flashcard.crearMultiopcion(
				"¿Cuál es el océano más grande?",
				"Pacífico",
				List.of("Atlántico", "Índico", "Ártico")
				);

		assertEquals("¿Cuál es el océano más grande?", f.getPregunta());
		assertEquals("Pacífico", f.getRespuestaCorrecta());
		assertEquals(4, f.getOpciones().size());
		assertTrue(f.getOpciones().contains("Ártico"));
		assertEquals(Flashcard.TipoFlashcard.MULTIOPCION, f.getTipo());
	}

	@Test
	void getOpcionesBarajadas_devuelveListaDiferentePeroEquivalente() {
		Flashcard f = Flashcard.crearMultiopcion(
				"Pregunta",
				"Correcta",
				List.of("A", "B", "C")
				);

		List<String> original = f.getOpciones();
		List<String> barajadas = f.getOpcionesBarajadas();

		assertEquals(4, barajadas.size());
		assertTrue(barajadas.containsAll(original));
		assertNotSame(original, barajadas); // debe ser una copia
	}

	@Test
	void esRespuestaCorrecta_funcionaSinImportarMayusculasNiEspacios() {
		Flashcard f = Flashcard.crearPreguntaAbierta("¿Color del cielo?", "Azul");

		assertTrue(f.esRespuestaCorrecta("azul"));
		assertTrue(f.esRespuestaCorrecta("  AZUL "));
		assertFalse(f.esRespuestaCorrecta("verde"));
	}

}
