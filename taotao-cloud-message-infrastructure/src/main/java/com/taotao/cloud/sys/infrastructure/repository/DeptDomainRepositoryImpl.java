package com.taotao.cloud.message.infrastructure.repository;

import com.taotao.cloud.message.domain.dept.entity.DeptEntity;
import com.taotao.cloud.message.domain.dept.repository.DeptDomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeptDomainRepositoryImpl implements DeptDomainRepository {


	@Override
	public void create(DeptEntity dept) {

	}

	@Override
	public void modify(DeptEntity dept) {

	}

	@Override
	public void remove(Long[] ids) {

	}
}
