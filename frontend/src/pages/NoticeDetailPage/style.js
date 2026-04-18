import styled from '@emotion/styled';

export const Container = styled.div`
    padding: 32px;
    background-color: ${props => props.theme.colors.background};
    min-height: calc(100vh - ${props => props.theme.size.headerHeight});
    font-family: ${props => props.theme.fonts.family};
`;

export const BackButtonWrapper = styled.div`
    display: flex;
    justify-content: flex-end;
    margin-bottom: 20px;
`;

export const BackButton = styled.button`
    background: none;
    border: none;
    color: ${props => props.theme.colors.textSub};
    font-size: ${props => props.theme.fonts.size.sm};
    cursor: pointer;
    padding: 0;

    &:hover {
        color: ${props => props.theme.colors.primary};
    }
`;

export const Card = styled.div`
    background: ${props => props.theme.colors.white};
    border-radius: 12px;
    padding: 32px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
`;

export const Title = styled.h2`
    font-size: ${props => props.theme.fonts.size.xl};
    font-weight: ${props => props.theme.fonts.weight.bold};
    color: ${props => props.theme.colors.textMain};
    margin-bottom: 12px;
`;

export const Meta = styled.div`
    display: flex;
    gap: 16px;
    font-size: ${props => props.theme.fonts.size.sm};
    color: ${props => props.theme.colors.textSub};
`;

export const Divider = styled.hr`
    border: none;
    border-top: 1px solid ${props => props.theme.colors.border};
    margin: 20px 0;
`;

export const Content = styled.p`
    font-size: ${props => props.theme.fonts.size.base};
    color: ${props => props.theme.colors.textMain};
    line-height: 1.8;
    white-space: pre-wrap;
    min-height: 300px;
`;
