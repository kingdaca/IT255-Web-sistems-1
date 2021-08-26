/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "marka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marka.findAll", query = "SELECT m FROM Marka m")
    , @NamedQuery(name = "Marka.findByIdMarka", query = "SELECT m FROM Marka m WHERE m.idMarka = :idMarka")
    , @NamedQuery(name = "Marka.findByNaziv", query = "SELECT m FROM Marka m WHERE m.naziv = :naziv")})
public class Marka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_marka")
    private Integer idMarka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "markaId")
    private Collection<Model> modelCollection;

    public Marka() {
    }

    public Marka(Integer idMarka) {
        this.idMarka = idMarka;
    }

    public Marka(Integer idMarka, String naziv) {
        this.idMarka = idMarka;
        this.naziv = naziv;
    }

    public Integer getIdMarka() {
        return idMarka;
    }

    public void setIdMarka(Integer idMarka) {
        this.idMarka = idMarka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public Collection<Model> getModelCollection() {
        return modelCollection;
    }

    public void setModelCollection(Collection<Model> modelCollection) {
        this.modelCollection = modelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarka != null ? idMarka.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marka)) {
            return false;
        }
        Marka other = (Marka) object;
        if ((this.idMarka == null && other.idMarka != null) || (this.idMarka != null && !this.idMarka.equals(other.idMarka))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Marka[ idMarka=" + idMarka + " ]";
    }
    
}
