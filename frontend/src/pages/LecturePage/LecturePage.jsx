import React, { useState } from 'react';
import * as S from './style';
import { FaPlayCircle, FaFileAlt, FaBullhorn } from 'react-icons/fa';
import { useQuery } from '@tanstack/react-query';
import { getLectureInfo } from '../../api/lectureApi';
import { useParams, useSearchParams } from 'react-router-dom';
import LoadingDisplay from '../../components/common/LoadingDisplay/LoadingDisplay';
import { getAssignmentInfo } from '../../api/assignmentApi';
import { getNoticeInfo } from '../../api/noticeApi';

function LecturePage() {

    const param = useParams();
    const [searchParams, setSearchParams] = useSearchParams();

    const activeWeek = Number(searchParams.get('week')) || 0;

    /* 클릭 시 URL을 ?week=숫자 로 변경 */
    const handleTabClick = (week) => {
        setSearchParams({ week: week }); // 
    };

    /* 전체 강의 정보 가져오기 */
    const { data: allLectures, isLoading: isLectureLoading } = useQuery({
        queryKey: ['lectures', param?.courseId],
        queryFn: () => getLectureInfo(param?.courseId, null),
        enabled: !!param?.courseId,
    })

    /* 주차별 과제 정보 가져오기 */
    const { data: assignmentsInfo, isFetching: isAssignmentFetching } = useQuery({
        queryKey: ['assignments', param?.courseId, activeWeek],
        queryFn: () => getAssignmentInfo(param?.courseId, activeWeek),
        enabled: !!param?.courseId && activeWeek !== 0,
        placeholderData: (previousData) => previousData,
    })

    /* 주차별 공지 정보 가져오기 */
    const { data: noticesInfo, isFetching: isNoticeFetching } = useQuery({
        queryKey: ["notices", param?.courseId, activeWeek],
        queryFn: () => getNoticeInfo(param?.courseId, activeWeek),
        enabled: !!param?.courseId && activeWeek !== 0,
        placeholderData: (previousData) => previousData,
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
                    onClick={() => handleTabClick(0)}
                >
                    전체
                </S.TabItem>

                {weeks?.map((week) => (
                    <S.TabItem
                        key={week}
                        value={week}
                        active={activeWeek === week}
                        onClick={() => handleTabClick(week)}
                    >
                        {week}주차
                    </S.TabItem>
                ))}
            </S.TabList>

            {/* 1. 강의 목록 영역 */}
            <S.ListContainer>

                <S.ListHeader>
                    {activeWeek === 0 ? "전체" : `${activeWeek}주차 학습 및 과제`}
                </S.ListHeader>

                {isLectureLoading ? (
                    <LoadingDisplay type="loading" message="강의 목록을 가져오고 있습니다." />
                ) : (
                    <>
                        {
                            displayLectures?.map((lecture, index) => (
                                <S.ListItem key={lecture?.lectureId || index}>
                                    <S.ItemMain>
                                        <S.IconBox><FaPlayCircle /></S.IconBox>
                                        <S.ItemTitle isLecture>
                                            {lecture?.week}-{lecture?.chapter} {lecture?.title}
                                        </S.ItemTitle>
                                    </S.ItemMain>
                                    <S.ActionButton>다시보기</S.ActionButton>
                                </S.ListItem>
                            ))

                        }

                        {/* 2. 과제 목록 영역 */}
                        {activeWeek !== 0 && assignmentsInfo && assignmentsInfo.length > 0 && (
                            assignmentsInfo?.map((assignment) => (
                                <S.ListItem highlight={assignment?.subStatus === 'N'} key={assignment?.assignmentId}>

                                    <S.ItemMain>
                                        <S.IconBox isRed={assignment?.subStatus === 'N'}><FaFileAlt /></S.IconBox>
                                        <S.ItemText>
                                            <S.ItemTitle isUnread={assignment?.subStatus === 'N'}>
                                                {assignment?.title}
                                            </S.ItemTitle>

                                            <S.ItemInfo $isPeriod={assignment?.isPeriod === 'Y'} $isDone={assignment?.subStatus === 'Y'}>
                                                {assignment?.isPeriod === 'Y' ? '진행 중' : '기간 종료'} •
                                                {assignment?.subStatus === 'Y' ? ' 제출 완료' : ' 미제출'}
                                            </S.ItemInfo>
                                        </S.ItemText>
                                    </S.ItemMain>

                                    <S.ActionButton variant={assignment?.subStatus === 'Y' ? 'success' : 'primary'}>
                                        {assignment?.subStatus === 'Y' ? '수정하기' : '과제 제출'}
                                    </S.ActionButton>

                                </S.ListItem>
                            ))
                        )}

                        {/* 3. 공지 목록 영역 */}
                        {activeWeek !== 0 && noticesInfo && noticesInfo.length > 0 && (
                            noticesInfo?.map((notice) => (
                                <S.ListItem isNew={notice?.readStatus === 'N'} key={notice?.noticeId}>

                                    <S.ItemMain>
                                        <S.IconBox><FaBullhorn /></S.IconBox>
                                        <S.ItemText >
                                            <S.ItemTitle isUnread={notice?.readStatus === 'N'}>
                                                {notice?.title}
                                            </S.ItemTitle>
                                            
                                            <S.ItemInfo $isDone={notice?.readStatus === 'Y'}>
                                                {notice?.readStatus === 'Y' ? '읽음' : '안 읽음'}
                                            </S.ItemInfo>
                                        </S.ItemText>
                                    </S.ItemMain>

                                    <S.ActionButton variant={notice?.readStatus === 'Y' ? 'default' : 'primary'}>
                                        {notice?.readStatus === 'Y' ? '다시보기' : '확인하기'}
                                    </S.ActionButton>

                                </S.ListItem>
                            ))
                        )}

                    </>
                )}
            </S.ListContainer>

        </S.Container>
    );
}

export default LecturePage;