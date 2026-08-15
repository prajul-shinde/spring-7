package com.omasai.spring_7_reactive_examples.repositories;

import com.omasai.spring_7_reactive_examples.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class PersonRepositoryImplTest {

    PersonRepository personRepository = new PersonRepositoryImpl();

    // way you should not do
    @Test
    void testMonoByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);
        Person person = personMono.block();
        System.out.println(person.toString());
    }

    @Test
    void testMonoByIdSubscriber() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    void testGetByIdFoundStepVerifier() {
        Mono<Person> personMono = personRepository.getById(3);

        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();

        personMono.subscribe(person -> {
            System.out.println(person.getFirstName());
        });
    }

    @Test
    void testGetByIdNotFoundStepVerifier() {
        Mono<Person> personMono = personRepository.getById(8);

        StepVerifier.create(personMono).expectNextCount(0).verifyComplete();
        personMono.subscribe(System.out::println);
    }

    @Test
    void testMapOperation() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void testFluxBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();
        Person person = personFlux.blockFirst();
        System.out.println(person.toString());
    }

    @Test
    void testFluxSubscriber() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(System.out::println);
    }

    @Test
    void testFluxMap() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void testFluxToList() {
        Flux<Person> personFlux = personRepository.findAll();
        Mono<List<Person>> listMono = personFlux.collectList();
        listMono.subscribe(list -> list.forEach(System.out::println));
    }

    @Test
    void testFilterOnName() {
        personRepository.findAll()
                .filter(p -> p.getFirstName()
                        .equals("Fiona"))
                .subscribe(person -> System.out.println(person.getFirstName()));
    }

    @Test
    void testGeById() {
        Mono<Person> personMono = personRepository.findAll()
                .filter(p -> p.getFirstName()
                        .equals("Fiona")).next();
        personMono.subscribe(p -> System.out.println(p.getFirstName()));
    }

    @Test
    void testGetByIdNotFound() {
        Flux<Person> personFlux = personRepository.findAll();
        Integer id = 8;
        Mono<Person> personMono = personFlux.filter(p -> p.getId().equals(id))
                .single()
                .doOnError(throwable ->
                        System.out.println("error occurred in flux: " +
                                throwable.getMessage()));
        personMono.subscribe(System.out::println,
                throwable ->
                        System.out.println("error occurred in mono: " + throwable.getMessage()));

    }

}