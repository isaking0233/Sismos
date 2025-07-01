package mx.ipn.escom.loginsystem.dto;

public class HazardZoneDTO {
    public int latCell;
    public int lonCell;
    public long count;
    public double lambda;
    public double probability;

    public HazardZoneDTO(int latCell, int lonCell, long count, double lambda, double probability) {
        this.latCell = latCell;
        this.lonCell = lonCell;
        this.count = count;
        this.lambda = lambda;
        this.probability = probability;
    }
}
