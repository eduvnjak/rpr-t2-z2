package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetnaTacka, krajnjaTacka;
    private boolean pocetakPripada, krajPripada;

    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pocetakPripada, boolean krajPripada) {
        if(pocetnaTacka > krajnjaTacka) throw new IllegalArgumentException();
        this.pocetnaTacka = pocetnaTacka;
        this.krajnjaTacka = krajnjaTacka;
        this.pocetakPripada = pocetakPripada;
        this.krajPripada = krajPripada;
    }

    public Interval(){
        pocetnaTacka = 0;
        krajnjaTacka = 0;
        pocetakPripada = false;
        krajPripada = false;
    }

    public double getPocetnaTacka() {
        return pocetnaTacka;
    }

    public void setPocetnaTacka(double pocetnaTacka) {
        this.pocetnaTacka = pocetnaTacka;
    }

    public double getKrajnjaTacka() {
        return krajnjaTacka;
    }

    public void setKrajnjaTacka(double krajnjaTacka) {
        this.krajnjaTacka = krajnjaTacka;
    }

    public boolean isPocetakPripada() {
        return pocetakPripada;
    }

    public void setPocetakPripada(boolean pocetakPripada) {
        this.pocetakPripada = pocetakPripada;
    }

    public boolean isKrajPripada() {
        return krajPripada;
    }

    public void setKrajPripada(boolean krajPripada) {
        this.krajPripada = krajPripada;
    }

    public boolean isIn(double v) {
        if(this.isNull()) return false;
        if(v > pocetnaTacka && v < krajnjaTacka) return true;
        return (v == pocetnaTacka && pocetakPripada) || (v == krajnjaTacka && krajPripada);
    }

    public boolean isNull() {
        return pocetnaTacka == krajnjaTacka && (!krajPripada || !pocetakPripada);
    }

    public Interval intersect(Interval interval) {
        Interval presjek = new Interval();
        if(isIn(interval.getPocetnaTacka())){
            presjek.setPocetnaTacka(interval.getPocetnaTacka());
            presjek.setPocetakPripada(interval.isPocetakPripada());
        }
        if(isIn(interval.getKrajnjaTacka())){
            presjek.setKrajnjaTacka(interval.getKrajnjaTacka());
            presjek.setKrajPripada(interval.isKrajPripada());
        }
        if(interval.isIn(this.getPocetnaTacka())){
            presjek.setPocetnaTacka(this.getPocetnaTacka());
            presjek.setPocetakPripada(this.isPocetakPripada());
        }
        if(interval.isIn(this.getKrajnjaTacka())){
            presjek.setKrajnjaTacka(this.getKrajnjaTacka());
            presjek.setKrajPripada(this.isKrajPripada());
        }
        return presjek;
    }
    public static Interval intersect(Interval i1, Interval i2){
        return i1.intersect(i2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (Double.compare(interval.pocetnaTacka, pocetnaTacka) != 0) return false;
        if (Double.compare(interval.krajnjaTacka, krajnjaTacka) != 0) return false;
        if (pocetakPripada != interval.pocetakPripada) return false;
        return krajPripada == interval.krajPripada;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(pocetnaTacka);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(krajnjaTacka);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (pocetakPripada ? 1 : 0);
        result = 31 * result + (krajPripada ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        if(getPocetnaTacka() == 0 && getKrajnjaTacka() == 0 && !isKrajPripada() && !pocetakPripada) return "()";
        String pocetak = isPocetakPripada() ? "[" : "(";
        String kraj = isKrajPripada() ? "]" : ")";
        return pocetak + getPocetnaTacka() + "," + getKrajnjaTacka() + kraj;
    }
}
