package ru.chashka.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chashka.spring.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
// по сути через этот интерфейс мы манипулируем с самой таблицей в нашей базе данных
// модель: просто создаёт таблицу, а чтобы манипулировать с данными нам надо создать
//этот так называемый репозиторий

    // для каждой модели надо создать свой репозиторий!
    // репозиторий обычно интерфейс
}
