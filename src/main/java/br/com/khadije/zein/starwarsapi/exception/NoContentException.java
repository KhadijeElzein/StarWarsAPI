package br.com.khadije.zein.starwarsapi.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class NoContentException extends AbstractThrowableProblem {
	private static final long serialVersionUID = 7133153272485206277L;
	private String mensagem;


	public NoContentException() {
		super(null, "No content avaliable", Status.NO_CONTENT, "No content for this identification.");
	}
	public NoContentException(String mensagem) {
		super(null, "No content avaliable", Status.NO_CONTENT, mensagem);
		this.mensagem = mensagem;
	}
}
