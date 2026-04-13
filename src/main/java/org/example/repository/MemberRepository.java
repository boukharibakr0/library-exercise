package org.example.repository;

import org.example.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemberRepository {

    private final Map<String, Member> store = new HashMap<>();

    public void save(Member member) {
        store.put(member.getId(), member);
    }

    public Optional<Member> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean delete(String id) {
        return store.remove(id) != null;
    }

    public void clear() {
        store.clear();
    }
}
