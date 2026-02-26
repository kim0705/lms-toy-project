import React, { useState } from 'react';
import * as S from './style';
import { FaPlayCircle, FaFileAlt, FaBullhorn } from 'react-icons/fa';

function CoursesPage() {
    const [activeWeek, setActiveWeek] = useState(0);

    const weeklyInfo = [
        { week: 1, title: '자바의 정석 기초', homeworks: 2, notices: 1 },
        { week: 2, title: '객체지향 프로그래밍', homeworks: 1, notices: 0 },
        { week: 3, title: '예외처리와 컬렉션', homeworks: 0, notices: 1 },
    ];

    return (
        <S.Container>
            <S.Header>
                <S.Title>강의수강</S.Title>
                <S.Subtitle>수강 중인 강의를 이어서 학습해 보세요.</S.Subtitle>
            </S.Header>

            {/* 주차 선택 탭 */}
            <S.TabList>

                <S.TabItem
                    active={activeWeek === 0}
                    onClick={() => setActiveWeek(0)}
                >
                    전체
                </S.TabItem>
                
                {weeklyInfo.map((item) => (
                    <S.TabItem
                        key={item.week}
                        active={activeWeek === item.week}
                        onClick={() => setActiveWeek(item.week)}
                    >
                        {item.week}주차
                        {item.homeworks > 0 && <S.CountBadge>{item.homeworks}</S.CountBadge>}
                    </S.TabItem>
                ))}
            </S.TabList>

            {/* 주차별 상세 리스트 */}
            <S.ListContainer>
                <S.ListHeader>{activeWeek}주차 학습 및 과제</S.ListHeader>

                <S.ListItem>
                    <S.ItemMain>
                        <S.IconBox><FaPlayCircle /></S.IconBox>
                        <S.ItemText>
                            <span className="item-title">1차시 : 자바 환경 설정 및 헬로 월드</span>
                            <span className="item-info">동영상 강의 • 45분 • 학습 완료</span>
                        </S.ItemText>
                    </S.ItemMain>
                    <S.ActionBtn>다시보기</S.ActionBtn>
                </S.ListItem>

                <S.ListItem highlight>
                    <S.ItemMain>
                        <S.IconBox isRed><FaFileAlt /></S.IconBox>
                        <S.ItemText>
                            <span className="item-title">주차 과제 : 클래스 다이어그램 작성</span>
                            <span className="item-info urgent">마감 임박 • 미제출</span>
                        </S.ItemText>
                    </S.ItemMain>
                    <S.ActionBtn primary>과제 제출</S.ActionBtn>
                </S.ListItem>

                <S.ListItem>
                    <S.ItemMain>
                        <S.IconBox><FaBullhorn /></S.IconBox>
                        <S.ItemText>
                            <span className="item-title">이번 주 학습 가이드 안내</span>
                            <span className="item-info">공지사항</span>
                        </S.ItemText>
                    </S.ItemMain>
                    <S.ActionBtn>확인하기</S.ActionBtn>
                </S.ListItem>
            </S.ListContainer>
        </S.Container>
    );
}

export default CoursesPage;