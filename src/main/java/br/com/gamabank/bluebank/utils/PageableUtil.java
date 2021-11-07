package br.com.gamabank.bluebank.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableUtil {

	static int PAGE_MAX_SIZE = 20;

	public static Pageable pageRequest(Pageable pageable) {
		return PageableUtil.pageRequest(pageable, PAGE_MAX_SIZE);
	}

	public static Pageable pageRequest(Pageable pageable, int pageMaxSize) {
		int size = pageable.getPageSize();

		if (size > pageMaxSize || size < 0) {
			size = pageMaxSize;
		}

		return PageRequest.of(pageable.getPageNumber(), size, pageable.getSort());
	}

}
