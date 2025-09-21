package app.domain.model;

public class DiagnosticAid {
    private int diagnosticAidId;
    private String diagnosticAidName;
    private long price;

    public int getDiagnosticAidId() {
        return diagnosticAidId;
    }

    public void setDiagnosticAidId(int diagnosticAidId) {
        this.diagnosticAidId = diagnosticAidId;
    }

    public String getDiagnosticAidName() {
        return diagnosticAidName;
    }

    public void setDiagnosticAidName(String diagnosticAidName) {
        this.diagnosticAidName = diagnosticAidName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    }
