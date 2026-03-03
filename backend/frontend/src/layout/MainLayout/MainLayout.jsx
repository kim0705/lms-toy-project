import React from 'react';
import { Outlet } from 'react-router-dom';
import * as S from './style';
import Sidebar from '../Sidebar/Sidebar';

function MainLayout() {
    return (
        <S.LayoutWrapper>
            {/* 고정 사이드바 */}
            <Sidebar />

            {/* 컨텐츠 영역 */}
            <S.ContentArea>
                <S.MainContent>
                    <Outlet />
                </S.MainContent>
            </S.ContentArea>
        </S.LayoutWrapper>
    );
}

export default MainLayout;