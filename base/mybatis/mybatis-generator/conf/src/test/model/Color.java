package test.model;

public class Color {
    private String COLOR_ID;

    private String COLORCODE;

    private String COLORNAME;

    private String COLORRGB;

    private String ISUSED;

    public String getCOLOR_ID() {
        return COLOR_ID;
    }

    public void setCOLOR_ID(String COLOR_ID) {
        this.COLOR_ID = COLOR_ID == null ? null : COLOR_ID.trim();
    }

    public String getCOLORCODE() {
        return COLORCODE;
    }

    public void setCOLORCODE(String COLORCODE) {
        this.COLORCODE = COLORCODE == null ? null : COLORCODE.trim();
    }

    public String getCOLORNAME() {
        return COLORNAME;
    }

    public void setCOLORNAME(String COLORNAME) {
        this.COLORNAME = COLORNAME == null ? null : COLORNAME.trim();
    }

    public String getCOLORRGB() {
        return COLORRGB;
    }

    public void setCOLORRGB(String COLORRGB) {
        this.COLORRGB = COLORRGB == null ? null : COLORRGB.trim();
    }

    public String getISUSED() {
        return ISUSED;
    }

    public void setISUSED(String ISUSED) {
        this.ISUSED = ISUSED == null ? null : ISUSED.trim();
    }
}