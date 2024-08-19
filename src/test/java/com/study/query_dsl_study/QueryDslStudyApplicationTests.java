package com.study.query_dsl_study;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.query_dsl_study.entity.Hello;
import com.study.query_dsl_study.entity.QHello;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
class QueryDslStudyApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = QHello.hello;

		Hello result = query.selectFrom(qHello)
			.fetchOne();

		assertThat(result).isEqualTo(hello);
		assert result != null;
		assertThat(result.getId()).isEqualTo(hello.getId());
	}

}
