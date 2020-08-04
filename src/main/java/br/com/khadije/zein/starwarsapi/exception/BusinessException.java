package br.com.khadije.zein.starwarsapi.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends Exception {
	private static final long serialVersionUID = -3501400326240659417L;
	private String mensagem;
   

    public BusinessException(Throwable throwable) {
        super(throwable);
    }


    public BusinessException(String mensagem) {
        super();
        this.mensagem = mensagem;
    }
}