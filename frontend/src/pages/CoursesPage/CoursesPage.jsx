import React, { useState } from 'react';
import * as S from './style';
import { FaPlayCircle, FaFileAlt, FaBullhorn } from 'react-icons/fa';
import { useQuery } from '@tanstack/react-query';
import { getLectureInfo } from '../../api/lectureApi';
import { useParams } from 'react-router-dom';
import LoadingDisplay from '../../components/common/LoadingDisplay/LoadingDisplay';
import { getAssignmentInfo } from '../../api/assignmentApi';

function CoursesPage() {

    const param = useParams();
    const [activeWeek, setActiveWeek] = useState(0);

    /* 전체 강의 정보 가져오기 */
    const { data: allLectures, isLoading: isLectureLoading } = useQuery({
        queryKey: ['lectures', param?.courseId],
        queryFn: () => getLectureInfo(param?.courseId, null),
        enabled: !!param?.courseId,
    })

    /* 전체 과제 정보 가져오기 */
    const { data: allAssignments, isFetching: isAssignmentFetching } = useQuery({
        queryKey: ['assignments', param?.courseId, activeWeek],
        queryFn: () => getAssignmentInfo(param?.courseId, activeWeek),
        enabled: !!param?.courseId,
        placeholderData: (previousData) => previousData,
    })

    console.log(`현재 ${activeWeek}주차 과제 데이터:`, allAssignments);

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
                    </S.TabItem>
                ))}
            </S.TabList>

            {/* 1. 강의 목록 영역 */}
            <S.ListContainer>
                <S.ListHeader>
                    {activeWeek === 0 ? "전체" : `${activeWeek}주차`} 학습 및 과제
                </S.ListHeader>

                {isLectureLoading ? (
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

                        {/* 2. 과제 목록 영역 */}
                        {isAssignmentFetching ? (
                            <LoadingDisplay type="loading" message="과제 정보를 갱신 중입니다." />
                        ) : (
                            <>
                                {allAssignments && allAssignments.length > 0 ? (
                                    allAssignments.map((assignment) => (
                                        <S.ListItem highlight key={assignment?.assignmentId}>
                                            <S.ItemMain>
                                                <S.IconBox isRed><FaFileAlt /></S.IconBox>
                                                <S.ItemText>
                                                    <span className="item-title">{assignment?.title}</span>
                                                    <S.ItemInfo $isPeriod={assignment?.isPeriod === 'Y'}>
                                                        {assignment?.isPeriod === 'Y' ? '진행 중' : '기간 종료'} •
                                                        {assignment?.subStatus === 'Y' ? ' 제출 완료' : ' 미제출'}
                                                    </S.ItemInfo>
                                                </S.ItemText>
                                            </S.ItemMain>
                                            <S.ActionBtn primary={assignment?.subStatus === 'N'}>
                                                {assignment?.subStatus === 'Y' ? '수정하기' : '과제 제출'}
                                            </S.ActionBtn>
                                        </S.ListItem>
                                    ))
                                ) : (
                                    <LoadingDisplay type="empty" message="해당 주차에는 과제가 없습니다." />
                                )}
                            </>
                        )}

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