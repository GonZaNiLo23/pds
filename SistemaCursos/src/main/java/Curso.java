package models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "cursos")
public class Curso {

    public enum TipoEstrategia { SECUENCIAL, REPETICION, ALEATORIO }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    /** Fecha de alta del curso. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false,
            columnDefinition = "timestamp default current_timestamp")
    private Date fechaCreacion = new Date();

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_estrategia", nullable = false)
    private TipoEstrategia tipoEstrategia = TipoEstrategia.SECUENCIAL;

    @ManyToOne @JoinColumn(name = "creador_id")
    private Estudiante creador;

    @OneToMany(mappedBy = "curso",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.EAGER)
    private Set<Flashcard> flashcards = new HashSet<>();
    
    @Column(name = "es_publico", nullable = false)
    private boolean esPublico = true;

    protected Curso() { }                         // JPA

    public Curso(String nombre, String descripcion,
                 TipoEstrategia tipo, Set<Flashcard> fc,
                 Estudiante creador, boolean esPublico) {
        this.nombre         = nombre;
        this.descripcion    = descripcion;
        this.tipoEstrategia = tipo == null ? TipoEstrategia.SECUENCIAL : tipo;
        this.creador        = creador;
        this.esPublico		= esPublico;
        fc.forEach(this::addFlashcard);
    }

    /* dominio */
    public void addFlashcard(Flashcard f){ flashcards.add(f); f.setCurso(this); }

    /* getters/setters */
    public Long getId()              { return id; }
    public String getNombre()        { return nombre; }
    public void setNombre(String n)  { this.nombre = n; } 
    public String getDescripcion()   { return descripcion; }
    public void setDescripcion(String d){ this.descripcion = d; }

    public Date getFechaCreacion()   { return fechaCreacion; }

    public TipoEstrategia getTipoEstrategia()              { return tipoEstrategia; }
    public void setTipoEstrategia(TipoEstrategia t)        { this.tipoEstrategia = t; }

    public Estudiante getCreador()                         { return creador; }
    public void setCreador(Estudiante e)                   { this.creador = e; }

    public Set<Flashcard> getFlashcards()                 { return flashcards; }
    
    public boolean isPublico() { return esPublico; }
    public void setPublico(boolean esPublico) { this.esPublico = esPublico; }
}
