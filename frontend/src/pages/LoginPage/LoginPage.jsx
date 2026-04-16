import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import * as S from './style';
import { signIn } from '../../api/authApi';

/* 로그인 페이지: 아이디/비밀번호 입력 후 JWT 토큰 발급 및 저장 */
function LoginPage() {
    const navigate = useNavigate();
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    /* 로그인 폼 제출: 서버에 요청 후 토큰 저장 */
    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        try {
            const data = await signIn(userId, password);
            localStorage.setItem('accessToken', data.accessToken);
            localStorage.setItem('refreshToken', data.refreshToken);
            navigate('/');
        } catch (err) {
            setError(err?.message || '아이디 또는 비밀번호가 올바르지 않습니다.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <S.Wrapper>
            <S.Card>
                <S.Title>LMS</S.Title>
                <S.Subtitle>아이디와 비밀번호로 로그인하세요</S.Subtitle>

                <S.Form onSubmit={handleSubmit}>
                    <S.InputGroup>
                        <S.Label>아이디</S.Label>
                        <S.Input
                            type="text"
                            placeholder="아이디를 입력하세요"
                            value={userId}
                            onChange={(e) => setUserId(e.target.value)}
                            required
                        />
                    </S.InputGroup>

                    <S.InputGroup>
                        <S.Label>비밀번호</S.Label>
                        <S.Input
                            type="password"
                            placeholder="비밀번호를 입력하세요"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </S.InputGroup>

                    {error && <S.ErrorMessage>{error}</S.ErrorMessage>}

                    <S.SubmitButton type="submit" disabled={loading}>
                        {loading ? '로그인 중...' : '로그인'}
                    </S.SubmitButton>
                </S.Form>
            </S.Card>
        </S.Wrapper>
    );
}

export default LoginPage;
