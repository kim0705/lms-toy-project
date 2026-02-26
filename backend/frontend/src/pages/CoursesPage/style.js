import styled from '@emotion/styled';

export const Container = styled.div`
    padding: 32px;
    background-color: ${props => props.theme.colors.background};
    min-height: calc(100vh - ${props => props.theme.size.headerHeight});
`;

export const Header = styled.header`
    margin-bottom: 24px;
`;

export const Title = styled.h2`
    font-size: 24px;
    color: ${props => props.theme.colors.textMain};
    margin-bottom: 4px;
`;

export const Subtitle = styled.p`
    color: ${props => props.theme.colors.textSub};
    font-size: 14px;
`;

/* 탭 스타일 */
export const TabList = styled.div`
    display: flex;
    gap: 8px;
    margin-bottom: 32px;
    background: #e2e8f0;
    padding: 4px;
    border-radius: 12px;
    width: fit-content;
`;

export const TabItem = styled.button`
    display: flex;
    align-items: center;
    gap: 6px;
    padding: 8px 16px;
    border-radius: 8px;
    border: none;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s;
    background: ${props => props.active ? props.theme.colors.white : 'transparent'};
    color: ${props => props.active ? props.theme.colors.primary : props.theme.colors.textSub};
    box-shadow: ${props => props.active ? '0 2px 4px rgba(0,0,0,0.1)' : 'none'};

    &:hover {
        color: ${props => props.theme.colors.primary};
    }
`;

export const CountBadge = styled.span`
    background: #ef4444;
    color: white;
    font-size: 11px;
    padding: 1px 6px;
    border-radius: 10px;
`;

/* 리스트 스타일 */
export const ListContainer = styled.div`
    display: flex;
    flex-direction: column;
    gap: 12px;
`;

export const ListHeader = styled.h3`
    font-size: 16px;
    color: ${props => props.theme.colors.textMain};
    margin-bottom: 4px;
`;

export const ListItem = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 16px 20px;
    background: ${props => props.theme.colors.white};
    border-radius: 12px;
    border: 1px solid ${props => props.highlight ? props.theme.colors.primary : props.theme.colors.border};
    box-shadow: 0 1px 3px rgba(0,0,0,0.02);
`;

export const ItemMain = styled.div`
    display: flex;
    align-items: center;
    gap: 16px;
`;

export const IconBox = styled.div`
    font-size: 20px;
    color: ${props => props.isRed ? '#ef4444' : props.theme.colors.primary};
    display: flex;
    align-items: center;
`;

export const ItemText = styled.div`
    display: flex;
    flex-direction: column;
    
    .item-title {
        font-weight: 600;
        color: ${props => props.theme.colors.textMain};
    }
    .item-info {
        font-size: 12px;
        color: ${props => props.theme.colors.textSub};
    }
    .urgent {
        color: #ef4444;
        font-weight: 600;
    }
`;

export const ActionBtn = styled.button`
    padding: 8px 14px;
    border-radius: 6px;
    border: 1px solid ${props => props.primary ? 'transparent' : props.theme.colors.border};
    background: ${props => props.primary ? props.theme.colors.primary : props.theme.colors.white};
    color: ${props => props.primary ? props.theme.colors.white : props.theme.colors.textMain};
    font-size: 13px;
    font-weight: 600;
    cursor: pointer;

    &:hover {
        background: ${props => props.primary ? props.theme.colors.primaryHover : '#f8fafc'};
    }
`;