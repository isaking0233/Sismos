package mx.ipn.escom.loginsystem.model;

import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "earthquake")
public class Earthquake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "spatial_event")
    private String spatialEvent;

    @CsvBindByName(column = "magnitude")
    private Double magnitude;

    @CsvBindByName(column = "intensity")
    private String intensity;

    @CsvBindByName(column = "depth")
    private String depth;

    @CsvBindByName(column = "time")
    private String time;

    @CsvBindByName(column = "latitude")
    private Double latitude;

    @CsvBindByName(column = "longitude")
    private Double longitude;

    @CsvBindByName(column = "entity")
    private String entity;

    @CsvBindByName(column = "municipality")
    private String municipality;

    @CsvBindByName(column = "locality")
    private String locality;

    @CsvBindByName(column = "record_date")
    private String recordDate;

    @CsvBindByName(column = "source")
    private String source;

    @CsvBindByName(column = "observations")
    private String observations;

    @CsvBindByName(column = "representations")
    private String representation;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpatialEvent() {
        return spatialEvent;
    }

    public void setSpatialEvent(String spatialEvent) {
        this.spatialEvent = spatialEvent;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }
}
