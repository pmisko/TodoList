package pl.pmisko.Languages;

import java.util.Objects;

class LanguageDTO {
    private Integer id;
    private String code;

     public LanguageDTO(Integer id, String code) {
         this.id = id;
         this.code = code;
     }

     public LanguageDTO(Language language) {
        this.id= language.getId();
        this.code= language.getCode();
    }

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

     @Override
     public String toString() {
         return "LangDTO{" +
                 "id=" + id +
                 ", code='" + code + '\'' +
                 '}';
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LanguageDTO)) return false;
        LanguageDTO that = (LanguageDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode());
    }
}
