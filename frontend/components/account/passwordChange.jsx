import { Button } from '@mui/material'
import Link from 'next/link'

export default function PasswordChange() {
    return (
        <>
            <Link href='/account/pwchange'>
                <Button
                    color='primary'
                    type='submit'
                    variant='outlined'
                    fullWidth
                    style={{ verticalAlign: 'middle', color: '#000000' }}
                    sx={{ mt: 4.5, mb: 2, borderRadius: 12.5, boxShadow: 4 }}
                >
                    비밀번호 변경
                </Button>
            </Link>
        </>
    )
}
// 현재 상태 : 비밀번호 변경 화면 구현 (토큰은 아직)
