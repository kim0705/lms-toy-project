import React, { useState } from 'react';
import * as S from './style';
import { FaPlayCircle, FaFileAlt, FaBullhorn } from 'react-icons/fa';
import { useQuery } from '@tanstack/react-query';
import { getLectureInfo } from '../../api/lectureApi';
import { useParams } from 'react-router-dom';
import LoadingDisplay from '../../components/common/LoadingDisplay/LoadingDisplay';

function CoursesPage() {

    const param = useParams();
    const [activeWeek, setActiveWeek] = useState(0);

    /* 전체 강의 정보 가져오기 */
    const { data: allLectures, isLoading } = useQuery({
        queryKey: ['lectures', param?.courseId],
        queryFn: () => getLectureInfo(param?.courseId, null),
        enabled: !!param?.courseId,
    })

    /* 전체 데이터에서 '주차'만 정제 */
    const weeks = allLectures
        ? [...new Set(allLectures.map(item => item.week))].sort((a, b) => a - b)
        : [];


    /* 주차별 강의 정보 */
    const displayLectures = activeWeek === 0 ? allLectures : allLectures?.filter(lecture => lecture?.week === activeWeek);

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

                {weeks?.map((week) => (
                    <S.TabItem
                        key={week}
                        value={week}
                        active={activeWeek === week}
                        onClick={() => setActiveWeek(week)}
                    >
                        {week}주차
                        {/* {item?.homeworks > 0 && <S.CountBadge>{item?.homeworks}</S.CountBadge>} */}
                    </S.TabItem>
                ))}
            </S.TabList>

            {/* 주차별 상세 리스트 */}
            <S.ListContainer>
                <S.ListHeader>
                    {activeWeek === 0 ? "전체" : `${activeWeek}주차`} 학습 및 과제
                </S.ListHeader>

                {isLoading ? (
                    <LoadingDisplay type="loading" message="강의 목록을 가져오고 있습니다." />
                ) : (
                    <>
                        {displayLectures?.length === 0 ? (
                            <LoadingDisplay type="empty" message="해당 주차에는 강의가 없습니다." />
                        ) : (
                            displayLectures?.map((lecture, index) => (
                                <S.ListItem key={lecture?.lectureId || index}>
                                    <S.ItemMain>
                                        <S.IconBox><FaPlayCircle /></S.IconBox>
                                        <S.ItemText>
                                            <span className="item-title">{lecture?.week}-{lecture?.chapter} {lecture?.title}</span>
                                            <span className="item-info">동영상 강의 • {lecture?.reqTime}분 • 학습 완료</span>
                                        </S.ItemText>
                                    </S.ItemMain>
                                    <S.ActionBtn>다시보기</S.ActionBtn>
                                </S.ListItem>
                            ))
                        )}

                        {/* 정적 아이템들은 그대로 두기 */}
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
                    </>
                )}
            </S.ListContainer>
        </S.Container >
    );
}

export default CoursesPage;