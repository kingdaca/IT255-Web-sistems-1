/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@Entity
@Table(name = "artikal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artikal.findAll", query = "SELECT a FROM Artikal a")
    , @NamedQuery(name = "Artikal.findById", query = "SELECT a FROM Artikal a WHERE a.id = :id")
    , @NamedQuery(name = "Artikal.findByKubikaza", query = "SELECT a FROM Artikal a WHERE a.kubikaza = :kubikaza")
    , @NamedQuery(name = "Artikal.findBySnaga", query = "SELECT a FROM Artikal a WHERE a.snaga = :snaga")
    , @NamedQuery(name = "Artikal.findByCena", query = "SELECT a FROM Artikal a WHERE a.cena = :cena")
    , @NamedQuery(name = "Artikal.findByOpis", query = "SELECT a FROM Artikal a WHERE a.opis = :opis")
    , @NamedQuery(name = "Artikal.findByImage", query = "SELECT a FROM Artikal a WHERE a.image = :image")
    , @NamedQuery(name = "Artikal.findByModelId", query = "SELECT a FROM Artikal a WHERE a.modelId = :modelId")})
public class Artikal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "kubikaza")
    private String kubikaza;
    @Column(name = "snaga")
    private Integer snaga;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "cena")
    private String cena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "opis")
    private String opis;
    @Size(max = 2000)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "model_id", referencedColumnName = "id_model")
    @ManyToOne(optional = false)
    private Model modelId;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users idUser;

    public Artikal() {
    }

    public Artikal(Integer id) {
        this.id = id;
    }

    public Artikal(Integer id, String kubikaza, String cena, String opis, Model modelId) {
        this.id = id;
        this.kubikaza = kubikaza;
        this.cena = cena;
        this.opis = opis;
        this.modelId = modelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(String kubikaza) {
        this.kubikaza = kubikaza;
    }

    public Integer getSnaga() {
        return snaga;
    }

    public void setSnaga(Integer snaga) {
        this.snaga = snaga;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Model getModelId() {
        return modelId;
    }

    public void setModelId(Model modelId) {
        this.modelId = modelId;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikal)) {
            return false;
        }
        Artikal other = (Artikal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Artikal[ id=" + id + " ]";
    }

}
