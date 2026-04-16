import React, { useState } from 'react';
import * as S from './style';
import { useQuery } from '@tanstack/react-query';
import { useParams } from 'react-router-dom';
import { getNoticeList } from '../../api/noticeApi';
import LoadingDisplay from '../../components/common/LoadingDisplay/LoadingDisplay';

const SEARCH_TYPE_OPTIONS = [
    { value: 'all', label: '제목+내용' },
    { value: 'title', label: '제목' },
    { value: 'content', label: '내용' },
    { value: 'writer', label: '작성자' },
];

const SIZE_OPTIONS = [10, 20, 30];
const PAGE_GROUP_SIZE = 5;

/* 공지사항 게시판 페이지 */
function NoticePage() {
    const { courseId } = useParams();
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(10);
    const [searchType, setSearchType] = useState('all');
    const [keyword, setKeyword] = useState('');
    const [inputValue, setInputValue] = useState('');

    const { data, isLoading } = useQuery({
        queryKey: ['noticeList', courseId, page, size, keyword, searchType],
        queryFn: () => getNoticeList(courseId, { page, size, keyword, searchType }),
        enabled: !!courseId,
        placeholderData: (prev) => prev,
    });

    const notices = data?.content ?? [];
    const totalCount = data?.totalCount ?? 0;
    const totalPages = Math.ceil(totalCount / size);

    const pageGroupStart = Math.floor(page / PAGE_GROUP_SIZE) * PAGE_GROUP_SIZE;
    const pageGroupEnd = Math.min(pageGroupStart + PAGE_GROUP_SIZE, totalPages);

    /* 검색 실행: 페이지 초기화 후 입력값을 키워드로 적용 */
    const handleSearch = () => {
        setPage(0);
        setKeyword(inputValue);
    };

    /* Enter 키 입력 시 검색 실행 */
    const handleKeyDown = (e) => {
        if (e.key === 'Enter') handleSearch();
    };

    /* 페이지당 항목 수 변경 시 페이지 초기화 */
    const handleSizeChange = (e) => {
        setSize(Number(e.target.value));
        setPage(0);
    };

    /* 검색 유형 변경 시 페이지 초기화 */
    const handleSearchTypeChange = (e) => {
        setSearchType(e.target.value);
        setPage(0);
    };

    /* 날짜 포맷 변환: ISO 형식 → YYYY.MM.DD */
    const formatDate = (dateStr) => {
        if (!dateStr) return '-';
        return dateStr.slice(0, 10).replace(/-/g, '.');
    };

    return (
        <S.Container>
            <S.Header>
                <S.Title>공지사항</S.Title>
                <S.Subtitle>과목의 전체 공지사항을 확인하세요.</S.Subtitle>
            </S.Header>

            {/* 검색 영역 */}
            <S.SearchBar>
                <S.SizeSelect value={size} onChange={handleSizeChange}>
                    {SIZE_OPTIONS.map(s => (
                        <option key={s} value={s}>{s}개씩 보기</option>
                    ))}
                </S.SizeSelect>
                <S.SearchRight>
                    <S.SearchTypeSelect value={searchType} onChange={handleSearchTypeChange}>
                        {SEARCH_TYPE_OPTIONS.map(opt => (
                            <option key={opt.value} value={opt.value}>{opt.label}</option>
                        ))}
                    </S.SearchTypeSelect>
                    <S.SearchInput
                        type="text"
                        placeholder="검색어를 입력하세요"
                        value={inputValue}
                        onChange={(e) => setInputValue(e.target.value)}
                        onKeyDown={handleKeyDown}
                    />
                    <S.SearchButton onClick={handleSearch}>검색</S.SearchButton>
                </S.SearchRight>
            </S.SearchBar>

            {/* 공지 목록 테이블 */}
            <S.Table>
                <S.TableHead>
                    <S.TableRow>
                        <S.TableTh $width="80px">번호</S.TableTh>
                        <S.TableTh>제목</S.TableTh>
                        <S.TableTh $width="120px">작성자</S.TableTh>
                        <S.TableTh $width="120px">작성일</S.TableTh>
                    </S.TableRow>
                </S.TableHead>
                <S.TableBody>
                    {isLoading ? (
                        <S.TableRow>
                            <S.TableTd colSpan={4}>
                                <LoadingDisplay type="loading" message="공지사항을 가져오고 있습니다." />
                            </S.TableTd>
                        </S.TableRow>
                    ) : notices.length === 0 ? (
                        <S.TableRow>
                            <S.TableTd colSpan={4}>
                                <S.Empty>공지사항이 없습니다.</S.Empty>
                            </S.TableTd>
                        </S.TableRow>
                    ) : (
                        notices.map((notice, index) => (
                            <S.TableRow key={notice.noticeId} $hover>
                                <S.TableTd $center>{totalCount - (page * size) - index}</S.TableTd>
                                <S.TableTd>{notice.title}</S.TableTd>
                                <S.TableTd $center>{notice.writer}</S.TableTd>
                                <S.TableTd $center>{formatDate(notice.createdAt)}</S.TableTd>
                            </S.TableRow>
                        ))
                    )}
                </S.TableBody>
            </S.Table>

            {/* 페이지네이션 */}
            {totalPages > 1 && (
                <S.Pagination>
                    <S.PageButton
                        onClick={() => setPage(pageGroupStart - 1)}
                        disabled={pageGroupStart === 0}
                    >
                        이전
                    </S.PageButton>
                    {Array.from({ length: pageGroupEnd - pageGroupStart }, (_, i) => {
                        const pageNum = pageGroupStart + i;
                        return (
                            <S.PageButton
                                key={pageNum}
                                $active={pageNum === page}
                                onClick={() => setPage(pageNum)}
                            >
                                {pageNum + 1}
                            </S.PageButton>
                        );
                    })}
                    <S.PageButton
                        onClick={() => setPage(pageGroupEnd)}
                        disabled={pageGroupEnd >= totalPages}
                    >
                        다음
                    </S.PageButton>
                </S.Pagination>
            )}
        </S.Container>
    );
}

export default NoticePage;
