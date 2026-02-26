import styled from '@emotion/styled';
import { NavLink } from 'react-router-dom';

export const SidebarContainer = styled.aside`
    width: ${(props) => props.theme.size.sidebarWidth};
    background-color: ${(props) => props.theme.colors.primary};
    color: ${(props) => props.theme.colors.white};
    height: 100vh;
    display: flex;
    flex-direction: column;
    position: sticky;
    top: 0;
    box-shadow: 4px 0 10px rgba(0, 0, 0, 0.1);
`;

export const LogoArea = styled.div`
    padding: 40px 20px;
    font-size: 24px;
    font-weight: 900;
    text-align: center;
    letter-spacing: 1.5px;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);
`;

export const ProfileArea = styled.div`
    padding: 20px;
    display: flex;
    align-items: center;
    gap: 12px;
    border-bottom: 1px solid rgba(255, 255, 255, 0.1);
`;

export const UserInfo = styled.div`
    display: flex;
    flex-direction: column;
    .name {
        font-size: 14px;
        font-weight: bold;
    }
`;

export const NavSection = styled.nav`
    flex: 1;
    padding: 20px 0;
    overflow-y: auto;
`;

export const SectionTitle = styled.div`
    font-size: 11px;
    font-weight: bold;
    color: rgba(255, 255, 255, 0.4);
    padding: 20px 24px 8px;
    text-transform: uppercase;
    letter-spacing: 1px;
`;

export const CourseItem = styled.div`
    display: flex;
    align-items: center;
    padding: 12px 24px;
    color: rgba(255, 255, 255, 0.7);
    font-size: 14px;
    cursor: pointer;
    transition: all 0.2s ease;

    svg {
        margin-right: 12px;
        font-size: 18px;
    }

    &:hover {
        color: ${(props) => props.theme.colors.white};
        background-color: rgba(255, 255, 255, 0.1);
    }
`;

export const CourseSelectWrapper = styled.div`
    padding: 0 20px 10px;
    
    select {
        width: 100%;
        padding: 10px;
        border-radius: 8px;
        background-color: rgba(0, 0, 0, 0.2);
        color: white;
        border: 1px solid rgba(255, 255, 255, 0.2);
        font-size: 14px;
        outline: none;
        cursor: pointer;

        option {
            background-color: ${(props) => props.theme.colors.primary};
            color: white;
        }
    }
`;

export const MenuLink = styled(NavLink)`
    display: flex;
    align-items: center;
    padding: 16px 24px;
    color: rgba(255, 255, 255, 0.7);
    font-size: 16px;
    transition: all 0.3s ease;
    text-decoration: none;

    svg {
        margin-right: 12px;
        font-size: 22px;
    }

    &:hover {
        color: ${(props) => props.theme.colors.white};
        background-color: rgba(255, 255, 255, 0.1);
    }

    &.active {
        color: ${(props) => props.theme.colors.white};
        background-color: rgba(0, 0, 0, 0.15);
        font-weight: bold;
        position: relative;

        &::before {
            content: '';
            position: absolute;
            left: 0;
            width: 4px;
            height: 100%;
            background-color: ${(props) => props.theme.colors.white};
        }
    }
`;

export const Divider = styled.div`
    height: 1px;
    background-color: rgba(255, 255, 255, 0.1);
    margin: 15px 20px;
`;