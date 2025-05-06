package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /**
     * MemberServiceImpl은 MemberRepository라는 인터페이스뿐만 아니라 MemoryMemberRepository라는 구현체에도 의존하고 있음
     * 즉, DIP를 위반하고 있음
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
