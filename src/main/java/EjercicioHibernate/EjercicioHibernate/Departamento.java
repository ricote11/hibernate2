package EjercicioHibernate.EjercicioHibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// default package
// Generated 22 oct 2021 17:42:50 by Hibernate Tools 5.5.7.Final

/**
 * Departamento generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name="departamento")
public class Departamento implements java.io.Serializable {
	@Id
	@Column(name="codigo")
	private int codigo;
	@Column(name="nombre")
	private String nombre;
	@Column(name="conResponsable")
	private int codResponsable;

	public Departamento() {
	}

	public Departamento(int codigo, String nombre, int codResponsable) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.codResponsable = codResponsable;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodResponsable() {
		return this.codResponsable;
	}

	public void setCodResponsable(int codResponsable) {
		this.codResponsable = codResponsable;
	}

}
