package org.example.service;

import org.example.model.Member;
import org.example.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

/**
 * ============================================================
 *  GROUP 2 — Member Management
 * ============================================================
 *
 * Your task: implement every method that throws UnsupportedOperationException.
 * Do NOT modify the models, repositories, or tests.
 *
 * Git workflow:
 *   1. Create your branch:
 *        git checkout -b feature/member-management
 *   2. Implement the TODOs below
 *   3. Run the tests:
 *        ./gradlew test --tests "org.example.MemberServiceTest"
 *   4. Commit and push when all tests pass:
 *        git add .
 *        git commit -m "feat: implement MemberService"
 *        git push origin feature/member-management
 *   5. Open a Pull Request targeting the main branch
 * ============================================================
 */
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * Register a new member and return them.
     *
     * Validation rules (throw IllegalArgumentException if violated):
     *   - id, name and email must not be null or blank
     *   - email must contain '@'
     *   - a member with the same id must not already exist
     */
    public Member registerMember(String id, String name, String email) {
        // TODO: validate parameters
        // TODO: create a new Member and save it via the repository
        // TODO: return the saved member
        throw new UnsupportedOperationException("Not implemented yet — Group 2");
    }

    /**
     * Find a member by their id.
     * Return Optional.empty() if no member has that id.
     */
    public Optional<Member> findById(String id) {
        // TODO: delegate to the repository
        throw new UnsupportedOperationException("Not implemented yet — Group 2");
    }

    /**
     * Return all members whose name contains the given string (case-insensitive).
     * Return an empty list if none match.
     */
    public List<Member> findByName(String name) {
        // TODO: get all members from the repository and filter by name
        throw new UnsupportedOperationException("Not implemented yet — Group 2");
    }

    /**
     * Find a member by their exact email address (case-insensitive).
     * Return Optional.empty() if no member has that email.
     */
    public Optional<Member> findByEmail(String email) {
        // TODO: search all members for a matching email
        throw new UnsupportedOperationException("Not implemented yet — Group 2");
    }

    /**
     * Return all registered members.
     */
    public List<Member> listAll() {
        // TODO: delegate to the repository
        throw new UnsupportedOperationException("Not implemented yet — Group 2");
    }

    /**
     * Update the email address of an existing member.
     *
     * @throws IllegalArgumentException if the new email is blank or does not contain '@'
     * @throws IllegalArgumentException if no member with the given id exists
     */
    public Member updateEmail(String id, String newEmail) {
        // TODO: validate newEmail
        // TODO: find the member (throw IllegalArgumentException if not found)
        // TODO: update and save, then return the updated member
        throw new UnsupportedOperationException("Not implemented yet — Group 2");
    }

    /**
     * Remove a member by id.
     *
     * @return true if the member existed and was removed, false if not found
     */
    public boolean removeMember(String id) {
        // TODO: delegate to the repository
        throw new UnsupportedOperationException("Not implemented yet — Group 2");
    }
}
