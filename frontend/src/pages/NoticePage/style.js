import styled from '@emotion/styled';

export const Container = styled.div`
    padding: 32px;
    background-color: ${props => props.theme.colors.background};
    min-height: calc(100vh - ${props => props.theme.size.headerHeight});
    font-family: ${props => props.theme.fonts.family};
`;

export const Header = styled.header`
    margin-bottom: 24px;
`;

export const Title = styled.h2`
    font-size: ${props => props.theme.fonts.size.xxl};
    font-weight: ${props => props.theme.fonts.weight.bold};
    color: ${props => props.theme.colors.textMain};
    margin-bottom: 4px;
`;

export const Subtitle = styled.p`
    color: ${props => props.theme.colors.textSub};
    font-size: ${props => props.theme.fonts.size.sm};
    font-weight: ${props => props.theme.fonts.weight.regular};
`;

export const SearchBar = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 12px;
`;

export const SearchRight = styled.div`
    display: flex;
    align-items: center;
    gap: 8px;
`;

export const SizeSelect = styled.select`
    padding: 8px 12px;
    border: 1px solid ${props => props.theme.colors.border};
    border-radius: 6px;
    font-size: ${props => props.theme.fonts.size.sm};
    color: ${props => props.theme.colors.textMain};
    background: ${props => props.theme.colors.white};
    cursor: pointer;
    outline: none;
`;

export const SearchTypeSelect = styled.select`
    padding: 8px 12px;
    border: 1px solid ${props => props.theme.colors.border};
    border-radius: 6px;
    font-size: ${props => props.theme.fonts.size.sm};
    color: ${props => props.theme.colors.textMain};
    background: ${props => props.theme.colors.white};
    cursor: pointer;
    outline: none;
`;

export const SearchInput = styled.input`
    padding: 8px 12px;
    border: 1px solid ${props => props.theme.colors.border};
    border-radius: 6px;
    font-size: ${props => props.theme.fonts.size.sm};
    color: ${props => props.theme.colors.textMain};
    width: 220px;
    outline: none;

    &:focus {
        border-color: ${props => props.theme.colors.primary};
    }
`;

export const SearchButton = styled.button`
    padding: 8px 16px;
    background: ${props => props.theme.colors.primary};
    color: white;
    border: none;
    border-radius: 6px;
    font-size: ${props => props.theme.fonts.size.sm};
    font-weight: ${props => props.theme.fonts.weight.semibold};
    cursor: pointer;

    &:hover {
        background: ${props => props.theme.colors.primaryHover};
    }
`;

export const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
    background: ${props => props.theme.colors.white};
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
`;

export const TableHead = styled.thead`
    background: ${props => props.theme.colors.background};
`;

export const TableBody = styled.tbody``;

export const TableRow = styled.tr`
    border-bottom: 1px solid ${props => props.theme.colors.border};

    ${props => props.$hover && `
        cursor: pointer;
        &:hover td {
            background: ${props.theme.colors.background};
        }
    `}

    &:last-of-type {
        border-bottom: none;
    }
`;

export const TableTh = styled.th`
    padding: 14px 24px;
    text-align: ${props => props.$width ? 'center' : 'left'};
    font-size: ${props => props.theme.fonts.size.sm};
    font-weight: ${props => props.theme.fonts.weight.semibold};
    color: ${props => props.theme.colors.textSub};
    width: ${props => props.$width || 'auto'};
    border-bottom: 2px solid ${props => props.theme.colors.border};
`;

export const TableTd = styled.td`
    padding: 14px 24px;
    font-size: ${props => props.theme.fonts.size.sm};
    color: ${props => props.theme.colors.textMain};
    text-align: ${props => props.$center ? 'center' : 'left'};
    colSpan: ${props => props.colSpan};
`;

export const Empty = styled.div`
    padding: 48px 0;
    text-align: center;
    color: ${props => props.theme.colors.textSub};
    font-size: ${props => props.theme.fonts.size.sm};
`;

export const Pagination = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 4px;
    margin-top: 24px;
`;

export const PageButton = styled.button`
    min-width: 36px;
    height: 36px;
    padding: 0 8px;
    border-radius: 6px;
    font-size: ${props => props.theme.fonts.size.sm};
    font-weight: ${props => props.$active ? props.theme.fonts.weight.bold : props.theme.fonts.weight.regular};
    cursor: ${props => props.disabled ? 'not-allowed' : 'pointer'};
    background: ${props => props.$active ? props.theme.colors.primary : props.theme.colors.white};
    color: ${props => props.$active ? 'white' : props.theme.colors.textMain};
    border: 1px solid ${props => props.$active ? props.theme.colors.primary : props.theme.colors.border};
    opacity: ${props => props.disabled ? 0.4 : 1};

    &:hover:not(:disabled) {
        background: ${props => props.$active ? props.theme.colors.primaryHover : props.theme.colors.background};
    }
`;
