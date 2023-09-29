package med.voll.api.model;

import jakarta.persistence.Embeddable;
import med.voll.api.DTO.DatosDireccion;

@Embeddable
public class Direccion {
	private String calle;
	private String distrito;
	private String ciudad;
	private Integer numero;
	private String piso;
	
	public Direccion() {}
	
	public Direccion(DatosDireccion direccion) {
		this.calle = direccion.calle();
		this.distrito = direccion.distrito();
		this.ciudad = direccion.ciudad();
		this.numero = direccion.numero();
		this.piso = direccion.piso();
	}

	public String getCalle() {
		return calle;
	}

	public String getDistrito() {
		return distrito;
	}

	public String getCiudad() {
		return ciudad;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getPiso() {
		return piso;
	}

	public Direccion actualizarDireccion(DatosDireccion direccion) {
		this.calle = direccion.calle();
		this.distrito = direccion.distrito();
		this.ciudad = direccion.ciudad();
		this.numero = direccion.numero();
		this.piso = direccion.piso();
		return this;
	}
	
}
