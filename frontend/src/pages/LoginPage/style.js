import styled from '@emotion/styled';

export const Wrapper = styled.div`
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: ${({ theme }) => theme.colors.background};
`;

export const Card = styled.div`
    background-color: ${({ theme }) => theme.colors.white};
    border-radius: 12px;
    border: 1px solid ${({ theme }) => theme.colors.border};
    padding: 48px 40px;
    width: 100%;
    max-width: 400px;
`;

export const Title = styled.h1`
    font-size: ${({ theme }) => theme.fonts.size.xxl};
    font-weight: ${({ theme }) => theme.fonts.weight.bold};
    color: ${({ theme }) => theme.colors.primary};
    text-align: center;
    margin-bottom: 8px;
`;

export const Subtitle = styled.p`
    font-size: ${({ theme }) => theme.fonts.size.sm};
    color: ${({ theme }) => theme.colors.textSub};
    text-align: center;
    margin-bottom: 32px;
`;

export const Form = styled.form`
    display: flex;
    flex-direction: column;
    gap: 20px;
`;

export const InputGroup = styled.div`
    display: flex;
    flex-direction: column;
    gap: 6px;
`;

export const Label = styled.label`
    font-size: ${({ theme }) => theme.fonts.size.sm};
    font-weight: ${({ theme }) => theme.fonts.weight.medium};
    color: ${({ theme }) => theme.colors.textMain};
`;

export const Input = styled.input`
    padding: 10px 14px;
    border: 1px solid ${({ theme }) => theme.colors.border};
    border-radius: 8px;
    font-size: ${({ theme }) => theme.fonts.size.base};
    color: ${({ theme }) => theme.colors.textMain};
    outline: none;
    transition: border-color 0.2s;

    &:focus {
        border-color: ${({ theme }) => theme.colors.primary};
    }

    &::placeholder {
        color: ${({ theme }) => theme.colors.textSub};
    }
`;

export const ErrorMessage = styled.p`
    font-size: ${({ theme }) => theme.fonts.size.sm};
    color: ${({ theme }) => theme.colors.danger};
`;

export const SubmitButton = styled.button`
    padding: 12px;
    background-color: ${({ theme }) => theme.colors.primary};
    color: ${({ theme }) => theme.colors.white};
    border: none;
    border-radius: 8px;
    font-size: ${({ theme }) => theme.fonts.size.base};
    font-weight: ${({ theme }) => theme.fonts.weight.semibold};
    cursor: pointer;
    transition: background-color 0.2s;

    &:hover:not(:disabled) {
        background-color: ${({ theme }) => theme.colors.primaryHover};
    }

    &:disabled {
        opacity: 0.6;
        cursor: not-allowed;
    }
`;
