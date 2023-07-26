import Link from 'next/link'
import styles from './Footer.module.scss'

export default function Footer({}) {
  
  return (
    <footer className={styles.footerContainer}>
        <Link
            href="https://github.com/rominavarela"
            target="_blank"
            rel="noopener noreferrer"
            className={styles.footer}
        >
            Follow me @Github
        </Link>
    </footer>
  )
}