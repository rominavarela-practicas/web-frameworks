import { usePathname } from 'next/navigation'
import styles from './Layout.module.scss';
import Link from 'next/link'
import Footer from '../../components/footer/Footer.js';

export default function Layout({ children }) {
  const pathname = usePathname()
  const isHomePage = pathname === "/"
  const homeLink = !isHomePage
  return (
    <div className={styles.container}>
      { homeLink && (
        <Link href="/" className={styles.homeLink}> ← Home </Link>)}
      {children}
      <Footer/>
    </div>
  );
}