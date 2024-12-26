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
    ZONDA("ZONDA"),
    БЕЛКОММУНМАШ("БЕЛКОММУНМАШ"),
    БОГДАН("БОГДАН"),
    ВОЛЖАНИН("ВОЛЖАНИН"),
    ГАЗ("ГАЗ"),
    ГОЛАЗ("ГОЛАЗ"),
    ЗИЛ("ЗИЛ"),
    КАВЗ("КАВЗ"),
    КАМАЗ("КАМАЗ"),
    КРОНА("КРОНА"),
    ЛАЗ("ЛАЗ"),
    ЛИАЗ("ЛИАЗ"),
    МАЗ("МАЗ"),
    МАЗ_КУПАВА("МАЗ КУПАВА"),
    МАРЗ("МАРЗ"),
    НЕМАН("НЕМАН"),
    НЕФАЗ("НЕФАЗ"),
    ОЛИМП("ОЛИМП"),
    ПАЗ("ПАЗ"),
    ПРОМАВТО("ПРОМАВТО"),
    РОАЗ("РОАЗ"),
    СИБИРЬ_ТРЕЙЛЕР("СИБИРЬ ТРЕЙЛЕР"),
    СПЕЦТЕХПРОМ("СПЕЦТЕХПРОМ"),
    УРАЛ("УРАЛ"),
    УРАЛ_КУПАВА("УРАЛ КУПАВА"),
    УРАЛСПЕЦТРАНС("УРАЛСПЕЦТРАНС");

    private final String model;

    BusModel(String model) {
        this.model = model;
    }


    @Override
    public String toString() {
        return model;
    }
}