package br.com.bpd.common.validator;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public abstract class GenericValidator implements Validator {

	public static PageRequest validateListFields(int qtyItens, int indexPagination, int totalOfRegisters, String orderFieldParam, String orderParam) {
		orderFieldParam = GenericValidator.validateString(orderFieldParam);
		orderParam = GenericValidator.validateString(orderParam);
		
		if (qtyItens > 0 && indexPagination > 0 && totalOfRegisters > 0 && orderFieldParam != null && !orderFieldParam.isEmpty()) {
			int qtyPagination = ((totalOfRegisters + qtyItens - 1) / qtyItens);
			indexPagination = ((indexPagination <= (qtyPagination - 1)) ? indexPagination : ((qtyPagination - 1 >= 0) ? (qtyPagination - 1) : 0));
			
			int page = (indexPagination <= (qtyPagination - 1)) ? indexPagination : ((qtyPagination - 1 >= 0) ? (qtyPagination - 1) : 0);
			Sort sort;
			if (orderParam != null && !orderParam.isEmpty()) {
				sort = Sort.by((("asc".equals(orderParam.toLowerCase())) ? Direction.ASC : Direction.DESC), orderFieldParam);
			} else {
				sort = Sort.by(Direction.DESC, orderFieldParam);
			}
			
			return PageRequest.of(page, qtyItens, sort);
		} else {
			return null;
		}
	}

	public static String validateString(String field) {
		if (field != null) {
			field = commentChecker(field);
			field = javaScriptChecker(field);
			field = sqlInjectionChecker(field);
		}
		
		return field;
	}

	public static String commentChecker(String field) {
		if (field.contains("/*") && field.contains("*/")) {
			field = field.replace("/*", "");
			field = field.replace("*/", "");
		}
		
		return field;
	}

	public static String javaScriptChecker(String field) {
		if (field.contains("script")) {
			field = field.replace("script", "");
		}
		
		return field;
	}

	public static String sqlInjectionChecker(String field) {
		if (field.contains("--")) {
			field = field.replace("--", "");
		}
		
		return field;
	}

}
