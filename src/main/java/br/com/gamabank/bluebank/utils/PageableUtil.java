package br.com.gamabank.bluebank.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableUtil {

	static int PAGE_MAX_SIZE = 20;

	public static Pageable pageRequest(Pageable pageable) {
		int size = pageable.getPageSize();

		if (size > PAGE_MAX_SIZE || size < 0) {
			size = PAGE_MAX_SIZE;
		}

		return PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());
	}

}
