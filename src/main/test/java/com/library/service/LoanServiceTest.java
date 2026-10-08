package main.test.java.com.library.service;

import com.library.dto.IssueBookRequest;
import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.Member;
import com.library.model.enums.MemberRole;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.MemberRepository;
import com.library.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private LoanRepository loanRepository;
    private FineService fineService;
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        memberRepository = Mockito.mock(MemberRepository.class);
        loanRepository = Mockito.mock(LoanRepository.class);
        fineService = Mockito.mock(FineService.class);

        loanService = new LoanServiceImpl(bookRepository, memberRepository, loanRepository, fineService);
    }

    @Test
    void testIssueBookSuccess() {
        Book book = new Book("12345", "Test Title", "Author", "Category");
        Member member = new Member("M-101", "John", "john@example.com", MemberRole.MEMBER);

        when(bookRepository.findByIsbn("12345")).thenReturn(Optional.of(book));
        when(memberRepository.findById("M-101")).thenReturn(Optional.of(member));

        IssueBookRequest request = new IssueBookRequest();
        request.setIsbn("12345");
        request.setMemberId("M-101");

        Loan loan = loanService.issueBook(request);

        assertNotNull(loan);
        assertEquals("12345", loan.getBookIsbn());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }
}