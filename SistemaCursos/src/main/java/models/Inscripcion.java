package models;

import java.util.Date;

import javax.persistence.*;

import models.Curso.TipoEstrategia;

@Entity @Table(name = "inscripciones")
public class Inscripcion {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Column(length = 10)
	private String progreso;

	@Column(name = "estrategia_seleccionada", length = 50)
	private TipoEstrategia estrategiaSeleccionada;

	@ManyToOne(optional = false)
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;

	@ManyToOne(optional = false)    
	@JoinColumn(name = "curso_id")
	private Curso curso;

	protected Inscripcion() {} // JPA

	public Inscripcion(Estudiante estudiante, Curso curso, TipoEstrategia estrategia) {
		this.estudiante = estudiante;
		this.curso = curso;
		this.fechaInicio = new Date();
		this.progreso = "0%";
		this.estrategiaSeleccionada = estrategia;
	}

	/* getters/setters */
	public Long getId() { return id; }
	public Date getFechaInicio() { return fechaInicio; }
	public String getProgreso() { return progreso; }
	public void setProgreso(String progreso) { this.progreso = progreso; }
	public TipoEstrategia getEstrategiaSeleccionada() { return estrategiaSeleccionada; }
	public Estudiante getEstudiante() { return estudiante; }
	public Curso getCurso() { return curso; }
	
	public void actualizarProgreso(String nuevoProgreso) {
		this.progreso = nuevoProgreso;
	}
} 