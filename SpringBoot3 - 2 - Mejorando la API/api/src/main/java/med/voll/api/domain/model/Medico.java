package med.voll.api.domain.model;

import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import med.voll.api.domain.DTO.Especialidad;
import med.voll.api.domain.DTO.medico.DatosActualizarMedico;
import med.voll.api.domain.DTO.medico.DatosRegistroMedico;

@Entity(name = "Medico")
@Table(name = "medicos")
public class Medico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String documento;
	private String email;
	private String telefono;
	private Boolean activo;
	@Enumerated(EnumType.STRING)
	private Especialidad especialidad;
	@Embedded
	private Direccion direccion;
	
	public Medico() {}
	
	public Medico(DatosRegistroMedico datosRegistroMedico) {
		this.activo = true;
		this.nombre = datosRegistroMedico.nombre();
		this.documento = datosRegistroMedico.documento();
		this.email = datosRegistroMedico.email();
		this.telefono = datosRegistroMedico.telefono();
		this.especialidad = datosRegistroMedico.especialidad();
		this.direccion = new Direccion(datosRegistroMedico.direccion());
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(id, other.id);
	}

	public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
		if (datosActualizarMedico.nombre() != null) {
			this.nombre = datosActualizarMedico.nombre();
		}
		if (datosActualizarMedico.documento() != null) {
			this.documento = datosActualizarMedico.documento();
		}
		if (datosActualizarMedico.direccion() != null) {
			this.direccion = direccion.actualizarDireccion(datosActualizarMedico.direccion());
		}
	}

	public void darBaja() {
		this.activo = false;
	}
	
}
