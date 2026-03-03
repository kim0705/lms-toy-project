import { FaExclamationCircle } from 'react-icons/fa';
import * as S from './style';

function LoadingDisplay({ type, message }) {
    return (
        <S.StatusWrapper>
            {type === 'loading' ? (
                <>
                    <S.Spinner />
                    <p>{message || '잠시만 기다려주세요...'}</p>
                </>
            ) : (
                <>
                    <FaExclamationCircle />
                    <p>{message || '내용이 없습니다.'}</p>
                </>
            )}
        </S.StatusWrapper>
    );
}

export default LoadingDisplay;