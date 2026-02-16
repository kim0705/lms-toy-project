import React from 'react';
import * as S from './style';
import { FaPlayCircle, FaCheckCircle, FaClock } from 'react-icons/fa';

function CoursesPage() {
    const myCourses = [
        { id: 1, title: 'React 초급 마스터', instructor: '김철수 강사', progress: 75, lastStudy: '2024-03-15', image: 'https://via.placeholder.com/300x160?text=React' },
        { id: 2, title: 'Emotion으로 스타일 잡기', instructor: '이영희 강사', progress: 100, lastStudy: '2024-02-28', image: 'https://via.placeholder.com/300x160?text=Emotion' },
        { id: 3, title: 'Node.js 백엔드 입문', instructor: '박지성 강사', progress: 20, lastStudy: '2024-03-10', image: 'https://via.placeholder.com/300x160?text=NodeJS' },
        { id: 4, title: 'UI/UX 디자인 원론', instructor: '최사랑 강사', progress: 0, lastStudy: '아직 학습 전', image: 'https://via.placeholder.com/300x160?text=UIUX' },
    ];

    return (
        <S.Container>
            <S.Header>
                <S.Title>강의수강</S.Title>
                <S.Subtitle>수강 중인 강의를 이어서 학습해 보세요.</S.Subtitle>
            </S.Header>

            
        </S.Container>
    );
}

export default CoursesPage;