package org.example;

import org.example.model.Member;
import org.example.repository.MemberRepository;
import org.example.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private MemberRepository repository;
    private MemberService service;

    @BeforeEach
    void setUp() {
        repository = new MemberRepository();
        service = new MemberService(repository);
    }

    // -------------------------------------------------------------------------
    // registerMember
    // -------------------------------------------------------------------------

    @Test
    void registerMember_shouldReturnMemberWithCorrectFields() {
        Member member = service.registerMember("1", "Alice Dupont", "alice@example.com");

        assertNotNull(member);
        assertEquals("1", member.getId());
        assertEquals("Alice Dupont", member.getName());
        assertEquals("alice@example.com", member.getEmail());
        assertNotNull(member.getMembershipDate());
    }

    @Test
    void registerMember_shouldPersistInRepository() {
        service.registerMember("1", "Alice Dupont", "alice@example.com");

        assertTrue(repository.findById("1").isPresent());
    }

    @Test
    void registerMember_shouldThrowIfIdIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerMember("", "Alice", "alice@example.com"));
    }

    @Test
    void registerMember_shouldThrowIfNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerMember("1", null, "alice@example.com"));
    }

    @Test
    void registerMember_shouldThrowIfEmailHasNoAtSign() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerMember("1", "Alice", "notanemail"));
    }

    @Test
    void registerMember_shouldThrowIfEmailIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> service.registerMember("1", "Alice", "  "));
    }

    @Test
    void registerMember_shouldThrowIfIdAlreadyExists() {
        service.registerMember("1", "Alice", "alice@example.com");
        assertThrows(IllegalArgumentException.class,
                () -> service.registerMember("1", "Bob", "bob@example.com"));
    }

    // -------------------------------------------------------------------------
    // findById
    // -------------------------------------------------------------------------

    @Test
    void findById_shouldReturnMemberWhenFound() {
        service.registerMember("99", "Bob Martin", "bob@example.com");

        Optional<Member> result = service.findById("99");

        assertTrue(result.isPresent());
        assertEquals("99", result.get().getId());
    }

    @Test
    void findById_shouldReturnEmptyWhenNotFound() {
        assertTrue(service.findById("unknown").isEmpty());
    }

    // -------------------------------------------------------------------------
    // findByName
    // -------------------------------------------------------------------------

    @Test
    void findByName_shouldMatchPartialNameCaseInsensitive() {
        service.registerMember("1", "Alice Dupont", "alice@example.com");
        service.registerMember("2", "Alice Martin", "amartin@example.com");
        service.registerMember("3", "Bob Smith", "bob@example.com");

        List<Member> results = service.findByName("alice");

        assertEquals(2, results.size());
    }

    @Test
    void findByName_shouldReturnEmptyListWhenNoMatch() {
        service.registerMember("1", "Alice", "alice@example.com");

        assertTrue(service.findByName("Charlie").isEmpty());
    }

    // -------------------------------------------------------------------------
    // findByEmail
    // -------------------------------------------------------------------------

    @Test
    void findByEmail_shouldMatchCaseInsensitive() {
        service.registerMember("1", "Alice", "Alice@Example.com");

        Optional<Member> result = service.findByEmail("alice@example.com");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
    }

    @Test
    void findByEmail_shouldReturnEmptyWhenNotFound() {
        assertTrue(service.findByEmail("nobody@example.com").isEmpty());
    }

    // -------------------------------------------------------------------------
    // listAll
    // -------------------------------------------------------------------------

    @Test
    void listAll_shouldReturnAllMembers() {
        service.registerMember("1", "Alice", "alice@example.com");
        service.registerMember("2", "Bob", "bob@example.com");

        assertEquals(2, service.listAll().size());
    }

    @Test
    void listAll_shouldReturnEmptyListWhenNoMembers() {
        assertTrue(service.listAll().isEmpty());
    }

    // -------------------------------------------------------------------------
    // updateEmail
    // -------------------------------------------------------------------------

    @Test
    void updateEmail_shouldChangeEmailAndReturnUpdatedMember() {
        service.registerMember("1", "Alice", "old@example.com");

        Member updated = service.updateEmail("1", "new@example.com");

        assertEquals("new@example.com", updated.getEmail());
        assertEquals("new@example.com", service.findById("1").get().getEmail());
    }

    @Test
    void updateEmail_shouldThrowIfEmailInvalid() {
        service.registerMember("1", "Alice", "alice@example.com");
        assertThrows(IllegalArgumentException.class,
                () -> service.updateEmail("1", "notvalid"));
    }

    @Test
    void updateEmail_shouldThrowIfMemberNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> service.updateEmail("nonexistent", "new@example.com"));
    }

    // -------------------------------------------------------------------------
    // removeMember
    // -------------------------------------------------------------------------

    @Test
    void removeMember_shouldReturnTrueAndDeleteMember() {
        service.registerMember("1", "Alice", "alice@example.com");

        boolean removed = service.removeMember("1");

        assertTrue(removed);
        assertTrue(service.findById("1").isEmpty());
    }

    @Test
    void removeMember_shouldReturnFalseWhenMemberNotFound() {
        assertFalse(service.removeMember("nonexistent"));
    }
}
