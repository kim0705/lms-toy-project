import React from 'react';
import * as S from './style';
import { FaBook, FaChartBar, FaBullhorn, FaThLarge, FaArrowLeft, FaUserCircle, FaSignOutAlt } from 'react-icons/fa';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { getEnrollmentList } from '../../api/enrollmentApi';
import { useQuery } from '@tanstack/react-query';

function Sidebar() {
    const location = useLocation();
    const navigate = useNavigate();
    const { courseId } = useParams();

    const isInCourse = location.pathname.includes('/course/');

    const menuList = [
        { name: '강의수강', path: `/course/${courseId}/study`, icon: <FaBook /> },
        { name: '리포트', path: `/course/${courseId}/report`, icon: <FaChartBar /> },
        { name: '공지사항', path: `/course/${courseId}/notice`, icon: <FaBullhorn /> },
    ];

    /* 학생 수강 목록 가져오기 */
    const { data: coursesList } = useQuery({
        queryKey: ['courses'],
        queryFn: () => getEnrollmentList(),
    })

    /* 로그아웃: 토큰 제거 후 로그인 페이지로 이동 */
    const handleLogout = () => {
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        navigate('/login');
    };

    return (
        <S.SidebarContainer>
            {/* 상단 유저 정보 영역 */}
            <S.ProfileArea>
                <FaUserCircle size={30} />
                <S.UserInfo>
                    <span className="name">홍길동 학생</span>
                </S.UserInfo>
            </S.ProfileArea>

            <S.NavSection>
                {!isInCourse ? (
                    /* 1. 과목 진입 전 (대시보드 모드) */
                    <>
                        <S.MenuLink to="/" className={location.pathname === '/' ? 'active' : ''}>
                            <FaThLarge />
                            <span>대시보드</span>
                        </S.MenuLink>

                        <S.SectionTitle>내 수강 과목</S.SectionTitle>
                        {coursesList?.map((course) => (
                            <S.CourseItem
                                key={course.courseId}
                                onClick={() => navigate(`/course/${course.courseId}/study`)}
                            >
                                <FaBook /> <span>{course.courseTitle}</span>
                            </S.CourseItem>
                        ))}
                    </>
                ) : (
                    /* 2. 특정 과목 진입 후 (학습 모드) */
                    <>
                        <S.MenuLink to="/">
                            <FaArrowLeft />
                            <span>대시보드로 나가기</span>
                        </S.MenuLink>

                        <S.Divider />

                        <S.SectionTitle>다른 과목 선택</S.SectionTitle>
                        <S.CourseSelectWrapper>
                            <select
                                value={courseId}
                                onChange={(e) => navigate(`/course/${e.target.value}/study`)}
                            >
                                {coursesList?.map(course => (
                                    <option key={course.courseId} value={course.courseId}>{course.courseTitle}</option>
                                ))}
                            </select>
                        </S.CourseSelectWrapper>

                        <S.Divider />

                        <S.SectionTitle>학습 메뉴</S.SectionTitle>
                        {menuList?.map((menu) => (
                            <S.MenuLink key={menu.path} to={menu.path}>
                                {menu.icon}
                                <span>{menu.name}</span>
                            </S.MenuLink>
                        ))}
                    </>
                )}
            </S.NavSection>
            <S.BottomArea>
                <S.LogoutButton onClick={handleLogout}>
                    <FaSignOutAlt />
                    <span>로그아웃</span>
                </S.LogoutButton>
            </S.BottomArea>
        </S.SidebarContainer>
    );
}

export default Sidebar;