import React from 'react';
import * as S from './style';
import { useNavigate, useParams } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { getNoticeDetail } from '../../api/noticeApi';
import LoadingDisplay from '../../components/common/LoadingDisplay/LoadingDisplay';

function NoticeDetailPage() {
    const { courseId, noticeId } = useParams();
    const navigate = useNavigate();

    const { data: notice, isLoading } = useQuery({
        queryKey: ['noticeDetail', courseId, noticeId],
        queryFn: () => getNoticeDetail(courseId, noticeId),
        enabled: !!courseId && !!noticeId,
    });

    const formatDate = (dateStr) => {
        if (!dateStr) return '-';
        return dateStr.slice(0, 10).replace(/-/g, '.');
    };

    if (isLoading) return <LoadingDisplay type="loading" message="공지사항을 가져오고 있습니다." />;

    return (
        <S.Container>
            <S.BackButtonWrapper>
                <S.BackButton onClick={() => navigate(`/course/${courseId}/notice`)}>
                    목록으로 →
                </S.BackButton>
            </S.BackButtonWrapper>

            <S.Card>
                <S.Title>{notice?.title}</S.Title>
                <S.Meta>
                    <span>{notice?.writer}</span>
                    <span>{formatDate(notice?.createdAt)}</span>
                    <span>조회 {notice?.viewCount}</span>
                </S.Meta>
                <S.Divider />
                <S.Content>{notice?.content}</S.Content>
            </S.Card>
        </S.Container>
    );
}

export default NoticeDetailPage;
