package person.davino.demo.json.jackson.xml;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "DocumentElement")
public class DrugCatalogueResponse{
    @JacksonXmlProperty(localName = "Result")
    private String result;

    @JacksonXmlProperty(localName = "Error")
    private String error;

    @JacksonXmlProperty(localName = "DataTable")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DataTable> dataTableList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public List<DataTable> getDataTableList() {
        return dataTableList;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setDataTableList(List<DataTable> dataTableList) {
        this.dataTableList = dataTableList;
    }

    public static class DataTable {

        @JacksonXmlProperty(localName = "FREQUENCY")
        private String FREQUENCY;

        @JacksonXmlProperty(localName = "PHAM_STD_CODE")
        private String PHAM_STD_CODE;

        @JacksonXmlProperty(localName = "MATERIAL_PROPERTY")
        private String MATERIAL_PROPERTY;

        @JacksonXmlProperty(localName = "PHAM_NAME")
        private String PHAM_NAME;

        @JacksonXmlProperty(localName = "OUTPUT_XDZFFW")
        private String OUTPUT_XDZFFW;

        @JacksonXmlProperty(localName = "PHAM_UNIT")
        private String PHAM_UNIT;

        @JacksonXmlProperty(localName = "RETAIL_PRICE")
        private String RETAIL_PRICE;

        @JacksonXmlProperty(localName = "PZ_WENHAO")
        private String PZ_WENHAO;

        @JacksonXmlProperty(localName = "PRIMARY_CLASSIFICATION")
        private String PRIMARY_CLASSIFICATION;

        @JacksonXmlProperty(localName = "SECONDARY_CLASSIFICATION")
        private String SECONDARY_CLASSIFICATION;

        @JacksonXmlProperty(localName = "PHAM_FORM")
        private String PHAM_FORM;

        @JacksonXmlProperty(localName = "PRODUCT_FACTORY")
        private String PRODUCT_FACTORY;

        @JacksonXmlProperty(localName = "PACK_NUIT")
        private String PACK_NUIT;

        @JacksonXmlProperty(localName = "PHAM_NAME_OTHER")
        private String PHAM_NAME_OTHER;

        @JacksonXmlProperty(localName = "CONVERT_GENE")
        private String CONVERT_GENE;

        @JacksonXmlProperty(localName = "PHARMACY")
        private String PHARMACY;

        @JacksonXmlProperty(localName = "PHARMACY_CODE")
        private String PHARMACY_CODE;

        @JacksonXmlProperty(localName = "ADMINISTRATION")
        private String ADMINISTRATION;

        @JacksonXmlProperty(localName = "PHAM_SPEC")
        private String PHAM_SPEC;

        @JacksonXmlProperty(localName = "DOSE_PER_UNIT")
        private String DOSE_PER_UNIT;

        @JacksonXmlProperty(localName = "DOSE_UNIT")
        private String DOSE_UNIT;

        @JacksonXmlProperty(localName = "SJ_FLAG")
        private String SJ_FLAG;

        public String getFREQUENCY() {
            return FREQUENCY;
        }

        public void setFREQUENCY(String FREQUENCY) {
            this.FREQUENCY = FREQUENCY;
        }

        public String getPHAM_STD_CODE() {
            return PHAM_STD_CODE;
        }

        public void setPHAM_STD_CODE(String PHAM_STD_CODE) {
            this.PHAM_STD_CODE = PHAM_STD_CODE;
        }

        public String getMATERIAL_PROPERTY() {
            return MATERIAL_PROPERTY;
        }

        public void setMATERIAL_PROPERTY(String MATERIAL_PROPERTY) {
            this.MATERIAL_PROPERTY = MATERIAL_PROPERTY;
        }

        public String getPHAM_NAME() {
            return PHAM_NAME;
        }

        public void setPHAM_NAME(String PHAM_NAME) {
            this.PHAM_NAME = PHAM_NAME;
        }

        public String getOUTPUT_XDZFFW() {
            return OUTPUT_XDZFFW;
        }

        public void setOUTPUT_XDZFFW(String OUTPUT_XDZFFW) {
            this.OUTPUT_XDZFFW = OUTPUT_XDZFFW;
        }

        public String getPHAM_UNIT() {
            return PHAM_UNIT;
        }

        public void setPHAM_UNIT(String PHAM_UNIT) {
            this.PHAM_UNIT = PHAM_UNIT;
        }

        public String getRETAIL_PRICE() {
            return RETAIL_PRICE;
        }

        public void setRETAIL_PRICE(String RETAIL_PRICE) {
            this.RETAIL_PRICE = RETAIL_PRICE;
        }

        public String getPZ_WENHAO() {
            return PZ_WENHAO;
        }

        public void setPZ_WENHAO(String PZ_WENHAO) {
            this.PZ_WENHAO = PZ_WENHAO;
        }

        public String getPRIMARY_CLASSIFICATION() {
            return PRIMARY_CLASSIFICATION;
        }

        public void setPRIMARY_CLASSIFICATION(String PRIMARY_CLASSIFICATION) {
            this.PRIMARY_CLASSIFICATION = PRIMARY_CLASSIFICATION;
        }

        public String getSECONDARY_CLASSIFICATION() {
            return SECONDARY_CLASSIFICATION;
        }

        public void setSECONDARY_CLASSIFICATION(String SECONDARY_CLASSIFICATION) {
            this.SECONDARY_CLASSIFICATION = SECONDARY_CLASSIFICATION;
        }

        public String getPHAM_FORM() {
            return PHAM_FORM;
        }

        public void setPHAM_FORM(String PHAM_FORM) {
            this.PHAM_FORM = PHAM_FORM;
        }

        public String getPRODUCT_FACTORY() {
            return PRODUCT_FACTORY;
        }

        public void setPRODUCT_FACTORY(String PRODUCT_FACTORY) {
            this.PRODUCT_FACTORY = PRODUCT_FACTORY;
        }

        public String getPACK_NUIT() {
            return PACK_NUIT;
        }

        public void setPACK_NUIT(String PACK_NUIT) {
            this.PACK_NUIT = PACK_NUIT;
        }

        public String getPHAM_NAME_OTHER() {
            return PHAM_NAME_OTHER;
        }

        public void setPHAM_NAME_OTHER(String PHAM_NAME_OTHER) {
            this.PHAM_NAME_OTHER = PHAM_NAME_OTHER;
        }

        public String getCONVERT_GENE() {
            return CONVERT_GENE;
        }

        public void setCONVERT_GENE(String CONVERT_GENE) {
            this.CONVERT_GENE = CONVERT_GENE;
        }

        public String getPHARMACY() {
            return PHARMACY;
        }

        public void setPHARMACY(String PHARMACY) {
            this.PHARMACY = PHARMACY;
        }

        public String getPHARMACY_CODE() {
            return PHARMACY_CODE;
        }

        public void setPHARMACY_CODE(String PHARMACY_CODE) {
            this.PHARMACY_CODE = PHARMACY_CODE;
        }

        public String getADMINISTRATION() {
            return ADMINISTRATION;
        }

        public void setADMINISTRATION(String ADMINISTRATION) {
            this.ADMINISTRATION = ADMINISTRATION;
        }

        public String getPHAM_SPEC() {
            return PHAM_SPEC;
        }

        public void setPHAM_SPEC(String PHAM_SPEC) {
            this.PHAM_SPEC = PHAM_SPEC;
        }

        public String getDOSE_PER_UNIT() {
            return DOSE_PER_UNIT;
        }

        public void setDOSE_PER_UNIT(String DOSE_PER_UNIT) {
            this.DOSE_PER_UNIT = DOSE_PER_UNIT;
        }

        public String getDOSE_UNIT() {
            return DOSE_UNIT;
        }

        public void setDOSE_UNIT(String DOSE_UNIT) {
            this.DOSE_UNIT = DOSE_UNIT;
        }

        public String getSJ_FLAG() {
            return SJ_FLAG;
        }

        public void setSJ_FLAG(String SJ_FLAG) {
            this.SJ_FLAG = SJ_FLAG;
        }
    }
}