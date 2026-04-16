import React from 'react';
import * as S from './style';
import { FaPlayCircle, FaCheckCircle, FaClock } from 'react-icons/fa';

function Dashboard() {

    const coursesList = [
        { id: 1, title: 'React 초급 마스터', instructor: '김철수 강사', progress: 75, lastStudy: '2024-03-15', image: 'https://placehold.co/300x160?text=React' },
        { id: 2, title: 'Emotion으로 스타일 잡기', instructor: '이영희 강사', progress: 100, lastStudy: '2024-02-28', image: 'https://placehold.co/300x160?text=Emotion' },
        { id: 3, title: 'Node.js 백엔드 입문', instructor: '박지성 강사', progress: 20, lastStudy: '2024-03-10', image: 'https://placehold.co/300x160?text=NodeJS' },
    ];
    
    return (
        <S.Container>
            <S.Header>
                <S.Title>반가워요, 홍길동님! 👋</S.Title>
                <S.Subtitle>오늘도 열공해 볼까요? 학습 중인 강의가 3개 있습니다.</S.Subtitle>
            </S.Header>

            <S.CardGrid>
                {coursesList.map((course) => (
                    <S.CourseCard key={course.id}>
                        <S.CardImage src={course.image} alt={course.title} />
                        <S.CardContent>
                            <S.Instructor>{course.instructor}</S.Instructor>
                            <S.CourseTitle>{course.title}</S.CourseTitle>

                            <S.ProgressWrapper>
                                <S.ProgressText>
                                    <span>학습 진도율</span>
                                    <span>{course.progress}%</span>
                                </S.ProgressText>
                                <S.ProgressBar>
                                    <S.ProgressFill width={course.progress} />
                                </S.ProgressBar>
                            </S.ProgressWrapper>

                            <S.CardFooter>
                                <S.LastDate>
                                    <FaClock /> {course.lastStudy}
                                </S.LastDate>
                                <S.LearnButton isDone={course.progress === 100}>
                                    {course.progress === 100 ? (
                                        <><FaCheckCircle /> 수강완료</>
                                    ) : (
                                        <><FaPlayCircle /> 학습하기</>
                                    )}
                                </S.LearnButton>
                            </S.CardFooter>
                        </S.CardContent>
                    </S.CourseCard>
                ))}
            </S.CardGrid>

            {/* <S.SectionDivider /> */}

            {/* <DashboardSummary /> */}
        </S.Container>
    );
}

export default Dashboard;