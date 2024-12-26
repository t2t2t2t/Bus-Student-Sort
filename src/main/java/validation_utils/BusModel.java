package validation_utils;

public enum BusModel {
    ANKAI("ANKAI"),
    YATS("YATS"),
    BAW("BAW"),
    DAEWOO("DAEWOO"),
    FOTON("FOTON"),
    GOLDEN_DRAGON("GOLDEN DRAGON"),
    HIGER("HIGER"),
    HYUNDAI("HYUNDAI"),
    IRISBUS("IRISBUS"),
    ISUZU("ISUZU"),
    IVECO("IVECO"),
    IVECO_AMT("IVECO AMT"),
    JAC("JAC"),
    KIA("KIA"),
    KING_LONG("KING LONG"),
    LOTOS("LOTOS"),
    MERCEDES("MERCEDES"),
    NEOPLAN("NEOPLAN"),
    OTOKAR("OTOKAR"),
    SCANIA("SCANIA"),
    SETRA("SETRA"),
    SOLARIS("SOLARIS"),
    SUNLONG("SUNLONG"),
    TEMSA("TEMSA"),
    TOYOTA("TOYOTA"),
    TROLIGA("TROLIGA"),
    VAN_HOOL("VAN HOOL"),
    VDL("VDL"),
    VOLGABUS("VOLGABUS"),
    VOLVO("VOLVO"),
    YUTONG("YUTONG"),
    ZHONGTONG("ZHONGTONG"),
    ZONDA("ZONDA");

    private final String model;

    BusModel(String model) {
        this.model = model;
    }


    @Override
    public String toString() {
        return model;
    }
}